/* Copyright 2019 Streampunk Media Ltd.

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
*/

#include "AudioIO.h"
#include "naudiodonUtil.h"
#include "Memory.h"
#include <map>

namespace streampunk {

napi_ref AudioIO::constructorRef;

AudioIO::AudioIO(napi_env env, napi_callback_info info): mInstanceRef(nullptr) {
  napi_status status;
  bool hasInOptions, hasOutOptions;

  napi_value undef;
  status = napi_get_undefined(env, &undef);
  FLOATING_STATUS;
  napi_value inOptions = undef;
  napi_value outOptions = undef;

  size_t argc = 1;
  napi_value args[1];
  status = napi_get_cb_info(env, info, &argc, args, nullptr, nullptr);
  if (!(status == napi_ok && argc == 1)) {
    napi_throw_error(env, nullptr, "AudioIO constructor expects an options object argument");
    return;
  }

  napi_value optionsObj = args[0];
  napi_valuetype t;
  status = napi_typeof(env, optionsObj, &t);
  if (t != napi_object)
    status = napi_throw_type_error(env, nullptr, "AudioIO parameters must be an object");

  status = napi_has_named_property(env, optionsObj, "inOptions", &hasInOptions);
  FLOATING_STATUS;
  if (hasInOptions) {
    status = napi_get_named_property(env, optionsObj, "inOptions", &inOptions);
    FLOATING_STATUS;
    status = napi_typeof(env, inOptions, &t);
    if (t != napi_object)
      status = napi_throw_type_error(env, nullptr, "AudioIO inOptions must be an object");
  }

  status = napi_has_named_property(env, optionsObj, "outOptions", &hasOutOptions);
  FLOATING_STATUS;
  if (hasOutOptions) {
    status = napi_get_named_property(env, optionsObj, "outOptions", &outOptions);
    FLOATING_STATUS;
    status = napi_typeof(env, outOptions, &t);
    if (t != napi_object)
      status = napi_throw_type_error(env, nullptr, "AudioIO outOptions must be an object");
  }

  if (!hasInOptions && !hasOutOptions) {
    napi_throw_error(env, nullptr, "AudioIO constructor expects an inOptions and/or an outOptions object argument");
    return;
  }

  mPaContext = std::make_shared<PaContext>(env, hasInOptions ? inOptions : undef, hasOutOptions ? outOptions: undef);
}

napi_status AudioIO::Init(napi_env env) {
  napi_status status;
  napi_value constructor;

  napi_property_descriptor properties[] = {
    DECLARE_NAPI_METHOD("start", sStart),
    DECLARE_NAPI_METHOD("read", sRead),
    DECLARE_NAPI_METHOD("write", sWrite),
    DECLARE_NAPI_METHOD("quit", sQuit)
  };

  status = napi_define_class(env, "AudioIO", NAPI_AUTO_LENGTH, Construct, nullptr, 4, properties, &constructor);
  PASS_STATUS;

  status = napi_create_reference(env, constructor, 1, &constructorRef);
  PASS_STATUS;

  return status;
}

napi_value AudioIO::Construct(napi_env env, napi_callback_info info) {
  napi_status status;
  napi_value thisVal;

  size_t argc = 2;
  napi_value args[2];
  status = napi_get_cb_info(env, info, &argc, args, &thisVal, nullptr);
  CHECK_STATUS;

  AudioIO* audioIO = new AudioIO(env, info);

  bool pendingException = false;
  status = napi_is_exception_pending(env, &pendingException);
  CHECK_STATUS;

  if (!pendingException) {
    status = napi_wrap(env, thisVal, audioIO, Destruct, nullptr, &audioIO->mInstanceRef);
    CHECK_STATUS;
  }

  return thisVal;
}

void AudioIO::Destruct(napi_env env, void* data, void* hint) {
  napi_status status;
  AudioIO* audioIO = static_cast<AudioIO*>(data);
  status = napi_delete_reference(env, audioIO->mInstanceRef);
  FLOATING_STATUS;
  delete audioIO;
}

napi_status AudioIO::NewInstance(napi_env env, napi_value arg, napi_value* instance) {
  napi_status status;

  const int argc = 1;
  napi_value args[1] = { arg };

  napi_value constructor;
  status = napi_get_reference_value(env, constructorRef, &constructor);
  PASS_STATUS;

  status = napi_new_instance(env, constructor, argc, args, instance);
  PASS_STATUS;

  return status;
}

napi_value AudioIO::Start(napi_env env, napi_callback_info info) {
  napi_status status;
  napi_value result;

  mPaContext->start(env);

  status = napi_get_undefined(env, &result);
  CHECK_STATUS;

  return result;
}

void readExecute(napi_env env, void* data) {
  asyncCarrier* c = (asyncCarrier*) data;
  c->mChunk = c->mPaContext->pullInChunk(c->mNumBytes, c->mFinished);
}

void readComplete(napi_env env, napi_status asyncStatus, void* data) {
  asyncCarrier* c = (asyncCarrier*) data;
  napi_value result, buffer, ts, finInt, finished, err;
  std::string errStr;
  void* bufferData;

  if (asyncStatus != napi_ok) {
    c->status = asyncStatus;
    c->errorMsg = "Async read failed to complete";
  }
  REJECT_STATUS;

  c->status = napi_create_object(env, &result);
  REJECT_STATUS;
  if (c->mPaContext->getErrStr(errStr, /*isInput*/true)) {
    c->status = napi_create_string_utf8(env, errStr.c_str(), NAPI_AUTO_LENGTH, &err);
    REJECT_STATUS;
    c->status = napi_set_named_property(env, result, "err", err);
    REJECT_STATUS;
  } else {
    if (c->mChunk && c->mChunk->numBytes()) {
      c->status = napi_create_buffer_copy(env, c->mChunk->numBytes(), c->mChunk->buf(), &bufferData, &buffer);
      REJECT_STATUS;
      c->status = napi_create_uint32(env, c->mChunk->ts(), &ts);
      REJECT_STATUS;
      c->status = napi_set_named_property(env, buffer, "timestamp", ts);
      REJECT_STATUS;
    } else {
      c->status = napi_create_buffer_copy(env, 0, nullptr, &bufferData, &buffer);
      REJECT_STATUS;
    }
    c->status = napi_set_named_property(env, result, "buf", buffer);
    REJECT_STATUS;
    c->status = napi_create_uint32(env, c->mFinished, &finInt);
    REJECT_STATUS;
    c->status = napi_coerce_to_bool(env, finInt, &finished);
    REJECT_STATUS;
    c->status = napi_set_named_property(env, result, "finished", finished);
    REJECT_STATUS;
  }

  napi_status status;
  status = napi_resolve_deferred(env, c->_deferred, result);
  FLOATING_STATUS;

  tidyCarrier(env, c);
}

napi_value AudioIO::Read(napi_env env, napi_callback_info info) {
  napi_value resourceName, promise;

  if (!mPaContext->hasInput())
    NAPI_THROW_ERROR("AudioIO Read - cannot read from a output-only stream");

  asyncCarrier* c = new asyncCarrier;
  c->mPaContext = mPaContext;

  c->status = napi_create_promise(env, &c->_deferred, &promise);
  REJECT_RETURN;

  size_t argc = 1;
  napi_value args[1];
  c->status = napi_get_cb_info(env, info, &argc, args, nullptr, nullptr);
  REJECT_RETURN;

  if (argc != 1)
    NAPI_THROW_ERROR("AudioIO Read expects 1 argument");

  c->status = napi_get_value_uint32(env, args[0], &c->mNumBytes);
  if ((c->status != napi_number_expected) && (c->status != napi_ok))
    NAPI_THROW_ERROR("AudioIO Read expects a valid number of bytes as the first parameter");

  c->status = napi_create_string_utf8(env, "Read", NAPI_AUTO_LENGTH, &resourceName);
  REJECT_RETURN;
  c->status = napi_create_async_work(env, nullptr, resourceName, readExecute, readComplete,
    c, &c->_request);
  REJECT_RETURN;
  c->status = napi_queue_async_work(env, c->_request);
  REJECT_RETURN;

  return promise;
}

void writeExecute(napi_env env, void* data) {
  asyncCarrier* c = (asyncCarrier*) data;
  c->mPaContext->pushOutChunk(c->mChunk);
}

void writeComplete(napi_env env, napi_status asyncStatus, void* data) {
  asyncCarrier* c = (asyncCarrier*) data;
  napi_value result;
  std::string errStr;

  if (asyncStatus != napi_ok) {
    c->status = asyncStatus;
    c->errorMsg = "Async write failed to complete";
  }
  REJECT_STATUS;

  if (c->mPaContext->getErrStr(errStr, /*isInput*/false)) {
    c->status = napi_create_string_utf8(env, errStr.c_str(), NAPI_AUTO_LENGTH, &result);
    REJECT_STATUS;
  } else {
    c->status = napi_get_undefined(env, &result);
    REJECT_STATUS;
  }

  napi_status status;
  status = napi_resolve_deferred(env, c->_deferred, result);
  FLOATING_STATUS;

  tidyCarrier(env, c);
}

napi_value AudioIO::Write(napi_env env, napi_callback_info info) {
  napi_value resourceName, promise;
  bool isBuffer;

  if (!mPaContext->hasOutput())
    NAPI_THROW_ERROR("AudioIO Write - cannot write to an input-only stream");

  asyncCarrier* c = new asyncCarrier;
  c->mPaContext = mPaContext;

  c->status = napi_create_promise(env, &c->_deferred, &promise);
  REJECT_RETURN;

  size_t argc = 1;
  napi_value args[1];
  c->status = napi_get_cb_info(env, info, &argc, args, nullptr, nullptr);
  REJECT_RETURN;

  if (argc != 1)
    NAPI_THROW_ERROR("AudioIO Write expects 1 argument");

  c->status = napi_is_buffer(env, args[0], &isBuffer);
  REJECT_RETURN;
  if (!isBuffer)
    NAPI_THROW_ERROR("AudioIO Write expects a valid chunk buffer as the first parameter");
  c->mChunk = std::make_shared<Chunk>(env, args[0]);

  c->status = napi_create_string_utf8(env, "Write", NAPI_AUTO_LENGTH, &resourceName);
  REJECT_RETURN;
  c->status = napi_create_async_work(env, nullptr, resourceName, writeExecute, writeComplete,
    c, &c->_request);
  REJECT_RETURN;
  c->status = napi_queue_async_work(env, c->_request);
  REJECT_RETURN;

  return promise;
}

void quitExecute(napi_env env, void* data) {
  asyncCarrier* c = (asyncCarrier*) data;
  c->mPaContext->quit();
  c->mPaContext->stop(c->mStopFlag);
}

void quitComplete(napi_env env, napi_status asyncStatus, void* data) {
  asyncCarrier* c = (asyncCarrier*) data;
  napi_value result;

  c->status = napi_get_undefined(env, &result);
  REJECT_STATUS;

  napi_status status;
  status = napi_resolve_deferred(env, c->_deferred, result);
  FLOATING_STATUS;

  tidyCarrier(env, c);
}

napi_value AudioIO::Quit(napi_env env, napi_callback_info info) {
  napi_value resourceName, promise;
  size_t strLen;

  asyncCarrier* c = new asyncCarrier;
  c->mPaContext = mPaContext;

  c->status = napi_create_promise(env, &c->_deferred, &promise);
  REJECT_RETURN;

  size_t argc = 1;
  napi_value args[1];
  c->status = napi_get_cb_info(env, info, &argc, args, nullptr, nullptr);
  REJECT_RETURN;

  if (argc != 1)
    NAPI_THROW_ERROR("AudioIO Quit expects 1 argument");

  c->status = napi_get_value_string_utf8(env, args[0], nullptr, 0, &strLen);
  REJECT_RETURN;
  char* stopFlag = (char*) malloc(sizeof(char) * (strLen + 1));
  c->status = napi_get_value_string_utf8(env, args[0], stopFlag, strLen + 1, &strLen);
  REJECT_RETURN;

  std::string stopFlagStr = stopFlag;
  if ((0 != stopFlagStr.compare("WAIT")) && (0 != stopFlagStr.compare("ABORT")))
    NAPI_THROW_ERROR("AudioIO Quit expects \'WAIT\' or \'ABORT\' as the first argument");
  c->mStopFlag = (0 == stopFlagStr.compare("WAIT")) ? 
    PaContext::eStopFlag::WAIT : PaContext::eStopFlag::ABORT;

  c->status = napi_create_string_utf8(env, "Quit", NAPI_AUTO_LENGTH, &resourceName);
  REJECT_RETURN;
  c->status = napi_create_async_work(env, nullptr, resourceName, quitExecute, quitComplete,
    c, &c->_request);
  REJECT_RETURN;
  c->status = napi_queue_async_work(env, c->_request);
  REJECT_RETURN;

  return promise;
}

AudioIO* AudioIO::GetInstance(napi_env env, napi_callback_info info) {
  napi_status status;
  napi_value thisVal;

  size_t argc = 1;
  napi_value args[1];
  status = napi_get_cb_info(env, info, &argc, args, &thisVal, nullptr);
  CHECK_STATUS;

  AudioIO* audioIO = nullptr;
  status = napi_unwrap(env, thisVal, reinterpret_cast<void**>(&audioIO));
  CHECK_STATUS;

  return audioIO;
}

napi_value AudioIO::sStart(napi_env env, napi_callback_info info) {
  return GetInstance(env, info)->Start(env, info);
}

napi_value AudioIO::sRead(napi_env env, napi_callback_info info) {
  return GetInstance(env, info)->Read(env, info);
}

napi_value AudioIO::sWrite(napi_env env, napi_callback_info info) {
  return GetInstance(env, info)->Write(env, info);
}

napi_value AudioIO::sQuit(napi_env env, napi_callback_info info) {
  return GetInstance(env, info)->Quit(env, info);
}

} // namespace streampunk