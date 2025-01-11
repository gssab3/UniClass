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

#ifndef PARAMS_H
#define PARAMS_H

#include "node_api.h"
#include "naudiodonUtil.h"
#include <sstream>

namespace streampunk {

bool checkOptions(napi_env env, napi_value options) {
  napi_status status;
  napi_valuetype type = napi_undefined;

  status = napi_typeof(env, options, &type);
  return type == napi_undefined ? false : true;
}

bool unpackBool(napi_env env, napi_value tags, const std::string& key, bool dflt) {
  napi_status status;
  bool hasKey;
  napi_value val;
  bool result = dflt;

  status = napi_has_named_property(env, tags, key.c_str(), &hasKey);
  FLOATING_STATUS;

  if (hasKey) {
    status = napi_get_named_property(env, tags, key.c_str(), &val);
    FLOATING_STATUS;

    status = napi_get_value_bool(env, val, &result);
    FLOATING_STATUS;
  }
  return result;
}

uint32_t unpackNum(napi_env env, napi_value tags, const std::string& key, uint32_t dflt) {
  napi_status status;
  bool hasKey;
  napi_value val;
  uint32_t result = dflt;

  status = napi_has_named_property(env, tags, key.c_str(), &hasKey);
  FLOATING_STATUS;

  if (hasKey) {
    status = napi_get_named_property(env, tags, key.c_str(), &val);
    FLOATING_STATUS;

    status = napi_get_value_uint32(env, val, &result);
    FLOATING_STATUS;
  }
  return result;
} 

std::string unpackStr(napi_env env, napi_value tags, const std::string& key, std::string dflt) {
  napi_status status;
  bool hasKey;
  napi_value val;
  std::string result = dflt;
  size_t strLen;

  status = napi_has_named_property(env, tags, key.c_str(), &hasKey);
  FLOATING_STATUS;

  if (hasKey) {
    status = napi_get_named_property(env, tags, key.c_str(), &val);
    FLOATING_STATUS;

    status = napi_get_value_string_utf8(env, val, nullptr, 0, &strLen);
    FLOATING_STATUS;
    char* resultStr = (char*) malloc(sizeof(char) * (strLen + 1));
    status = napi_get_value_string_utf8(env, tags, resultStr, strLen + 1, &strLen);
    FLOATING_STATUS;

    result = std::string(resultStr);
    free(resultStr);
  }
  return result;
} 

class AudioOptions {
public:
  AudioOptions(napi_env env, napi_value tags)
    : mDeviceID(unpackNum(env, tags, "deviceId", 0xffffffff)),
      mSampleRate(unpackNum(env, tags, "sampleRate", 44100)),
      mChannelCount(unpackNum(env, tags, "channelCount", 2)),
      mSampleFormat(unpackNum(env, tags, "sampleFormat", 8)),
      mSampleBits(1 == mSampleFormat ? 32 : mSampleFormat),
      mMaxQueue(unpackNum(env, tags, "maxQueue", 2)),
      mFramesPerBuffer(unpackNum(env, tags, "framesPerBuffer", 0)),
      mCloseOnError(unpackBool(env, tags, "closeOnError", true))
  {}
  ~AudioOptions() {}

  uint32_t deviceID() const  { return mDeviceID; }
  uint32_t sampleRate() const  { return mSampleRate; }
  uint32_t channelCount() const  { return mChannelCount; }
  uint32_t sampleFormat() const  { return mSampleFormat; }
  uint32_t sampleBits() const  { return mSampleBits; }
  uint32_t maxQueue() const  { return mMaxQueue; }
  uint32_t framesPerBuffer() const  { return mFramesPerBuffer; }
  bool closeOnError() const  { return mCloseOnError; }

  std::string toString() const  { 
    std::stringstream ss;
    ss << "audio options: ";
    if (mDeviceID == 0xffffffff)
      ss << "default device, ";
    else
      ss << "device " << mDeviceID << ", ";
    ss << "sample rate " << mSampleRate << ", ";
    ss << "channels " << mChannelCount << ", ";
    ss << "bits per sample " << mSampleBits << ", ";
    ss << "max queue " << mMaxQueue << ", ";
    ss << "frames per buffer " << mFramesPerBuffer << ", ";
    ss << "close on error " << (mCloseOnError ? "true" : "false");
    return ss.str();
  }

private:
  uint32_t mDeviceID;
  uint32_t mSampleRate;
  uint32_t mChannelCount;
  uint32_t mSampleFormat;
  uint32_t mSampleBits;
  uint32_t mMaxQueue;
  uint32_t mFramesPerBuffer;
  bool mCloseOnError;
};

} // namespace streampunk

#endif
