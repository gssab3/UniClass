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

#ifndef AUDIOIO_H
#define AUDIOIO_H

#include "node_api.h"
#include "Memory.h"
#include "Chunks.h"
#include "PaContext.h"

namespace streampunk {

class PaContext;

struct asyncCarrier : carrier {
  ~asyncCarrier() {}
  std::shared_ptr<PaContext> mPaContext = 0;
  std::shared_ptr<Chunk> mChunk = 0;
  uint32_t mNumBytes = 0;
  bool mFinished = false;
  PaContext::eStopFlag mStopFlag = PaContext::eStopFlag(0);
};

class AudioIO {
public:
  static napi_ref constructorRef;
  static napi_status Init(napi_env env);
  static napi_value Construct(napi_env env, napi_callback_info info);
  static void Destruct(napi_env env, void* data, void* hint);
  static napi_status NewInstance(napi_env env, napi_value arg, napi_value* instance);

  AudioIO(napi_env env, napi_callback_info info);
  ~AudioIO() {}

private:
  std::shared_ptr<PaContext> mPaContext;
  napi_ref mInstanceRef;

  napi_value Start(napi_env env, napi_callback_info info);
  napi_value Read(napi_env env, napi_callback_info info);
  napi_value Write(napi_env env, napi_callback_info info);
  napi_value Quit(napi_env env, napi_callback_info info);

  static AudioIO* GetInstance(napi_env env, napi_callback_info info);
  static napi_value sStart(napi_env env, napi_callback_info info);
  static napi_value sRead(napi_env env, napi_callback_info info);
  static napi_value sWrite(napi_env env, napi_callback_info info);
  static napi_value sQuit(napi_env env, napi_callback_info info);
};

} // namespace streampunk

#endif
