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

#include "GetHostAPIs.h"
#include "naudiodonUtil.h"
#include <portaudio.h>

namespace streampunk {

napi_value getHostAPIs(napi_env env, napi_callback_info info) {
  napi_status status;
  napi_value result, hostApiArr, hostInfo;

  PaError errCode = Pa_Initialize();
  if (errCode != paNoError)
    NAPI_THROW_ERROR((std::string("Could not initialize PortAudio: ") + Pa_GetErrorText(errCode)).c_str());

  status = napi_create_object(env, &result);
  CHECK_STATUS;

  uint32_t numHostApis = Pa_GetHostApiCount();
  status = napi_create_array_with_length(env, numHostApis, &hostApiArr);
  CHECK_STATUS;

  int32_t defaultHostApi = Pa_GetDefaultHostApi();
  status = naud_set_int32(env, result, "defaultHostAPI", defaultHostApi);

  for (uint32_t i = 0; i < numHostApis; ++i) {
    const PaHostApiInfo *hostApi = Pa_GetHostApiInfo(i);
    status = napi_create_object(env, &hostInfo);
    CHECK_STATUS;
    status = naud_set_uint32(env, hostInfo, "id", i);
    CHECK_STATUS;
    status = naud_set_string_utf8(env, hostInfo, "name", hostApi->name);
    CHECK_STATUS;

    switch(hostApi->type) {
      case paInDevelopment:
        status = naud_set_string_utf8(env, hostInfo, "type", "InDevelopment");
        CHECK_STATUS;
        break;
      case paDirectSound:
        status = naud_set_string_utf8(env, hostInfo, "type", "DirectSound");
        CHECK_STATUS;
        break;
      case paMME:
        status = naud_set_string_utf8(env, hostInfo, "type", "MME");
        CHECK_STATUS;
        break;
      case paASIO:
        status = naud_set_string_utf8(env, hostInfo, "type", "ASIO");
        CHECK_STATUS;
        break;
      case paSoundManager:
        status = naud_set_string_utf8(env, hostInfo, "type", "SoundManager");
        CHECK_STATUS;
        break;
      case paCoreAudio:
        status = naud_set_string_utf8(env, hostInfo, "type", "CoreAudio");
        CHECK_STATUS;
        break;
      case paOSS:
        status = naud_set_string_utf8(env, hostInfo, "type", "OSS");
        CHECK_STATUS;
        break;
      case paALSA:
        status = naud_set_string_utf8(env, hostInfo, "type", "ALSA");
        CHECK_STATUS;
        break;
      case paAL:
        status = naud_set_string_utf8(env, hostInfo, "type", "AL");
        CHECK_STATUS;
        break;
      case paBeOS:
        status = naud_set_string_utf8(env, hostInfo, "type", "BeOS");
        CHECK_STATUS;
        break;
      case paWDMKS:
        status = naud_set_string_utf8(env, hostInfo, "type", "WDMKS");
        CHECK_STATUS;
        break;
      case paJACK:
        status = naud_set_string_utf8(env, hostInfo, "type", "JACK");
        CHECK_STATUS;
        break;
      case paWASAPI:
        status = naud_set_string_utf8(env, hostInfo, "type", "WASAPI");
        CHECK_STATUS;
        break;
      case paAudioScienceHPI:
        status = naud_set_string_utf8(env, hostInfo, "type", "AudioScienceHPI");
        CHECK_STATUS;
        break;
      default:
        status = naud_set_string_utf8(env, hostInfo, "type", "Unknown");
        CHECK_STATUS;
        break;
    }

    status = naud_set_uint32(env, hostInfo, "deviceCount", hostApi->deviceCount);
    CHECK_STATUS;
    status = naud_set_uint32(env, hostInfo, "defaultInput", hostApi->defaultInputDevice);
    CHECK_STATUS;
    status = naud_set_uint32(env, hostInfo, "defaultOutput", hostApi->defaultOutputDevice);
    CHECK_STATUS;
    status = napi_set_element(env, hostApiArr, i, hostInfo);
    CHECK_STATUS;
  }
  status = napi_set_named_property(env, result, "HostAPIs", hostApiArr);
  CHECK_STATUS;

  Pa_Terminate();
  return result;
}

} // namespace streampunk
