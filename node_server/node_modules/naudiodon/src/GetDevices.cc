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

#include "GetDevices.h"
#include "naudiodonUtil.h"
#include <portaudio.h>

namespace streampunk {

napi_value getDevices(napi_env env, napi_callback_info info) {
  napi_status status;
  napi_value result, devInfo;
  uint32_t numDevices;

  PaError errCode = Pa_Initialize();
  if (errCode != paNoError)
    NAPI_THROW_ERROR((std::string("Could not initialize PortAudio: ") + Pa_GetErrorText(errCode)).c_str());

  numDevices = Pa_GetDeviceCount();
  status = napi_create_array(env, &result);
  CHECK_STATUS;

  for (uint32_t i = 0; i < numDevices; ++i) {
    const PaDeviceInfo* deviceInfo = Pa_GetDeviceInfo(i);
    status = napi_create_object(env, &devInfo);
    CHECK_STATUS;
    status = naud_set_uint32(env, devInfo, "id", i);
    CHECK_STATUS;
    status = naud_set_string_utf8(env, devInfo, "name", deviceInfo->name);
    CHECK_STATUS;
    status = naud_set_uint32(env, devInfo, "maxInputChannels", deviceInfo->maxInputChannels);
    CHECK_STATUS;
    status = naud_set_uint32(env, devInfo, "maxOutputChannels", deviceInfo->maxOutputChannels);
    CHECK_STATUS;
    status = naud_set_uint32(env, devInfo, "defaultSampleRate", deviceInfo->defaultSampleRate);
    CHECK_STATUS;
    status = naud_set_uint32(env, devInfo, "defaultLowInputLatency", deviceInfo->defaultLowInputLatency);
    CHECK_STATUS;
    status = naud_set_uint32(env, devInfo, "defaultLowOutputLatency", deviceInfo->defaultLowOutputLatency);
    CHECK_STATUS;
    status = naud_set_uint32(env, devInfo, "defaultHighInputLatency", deviceInfo->defaultHighInputLatency);
    CHECK_STATUS;
    status = naud_set_uint32(env, devInfo, "defaultHighOutputLatency", deviceInfo->defaultHighOutputLatency);
    CHECK_STATUS;
    status = naud_set_string_utf8(env, devInfo, "hostAPIName", Pa_GetHostApiInfo(deviceInfo->hostApi)->name);
    CHECK_STATUS;
    status = napi_set_element(env, result, i, devInfo);
  }

  Pa_Terminate();
  return result;
}

} // namespace streampunk
