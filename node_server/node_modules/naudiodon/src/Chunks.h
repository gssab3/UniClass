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

#ifndef CHUNKS_H
#define CHUNKS_H

#include "node_api.h"
#include "naudiodonUtil.h"
#include "Memory.h"
#include "ChunkQueue.h"

namespace streampunk {

class Chunk {
public:
  Chunk (napi_env env, napi_value chunk): mTs(0.0) {
    napi_status status;

    uint8_t* data;
    size_t dataLen;
    status = napi_get_buffer_info(env, chunk, (void**) &data, &dataLen);
    FLOATING_STATUS;

    mChunk = Memory::makeNew(data, dataLen);
  }
  Chunk(std::shared_ptr<Memory> memory, double ts)
    : mChunk(memory), mTs(ts)
  {}
  ~Chunk() {}

  uint32_t numBytes() const { return mChunk ? mChunk->numBytes() : 0; }
  uint8_t *buf() const { return mChunk ? mChunk->buf() : nullptr; }
  double ts() const { return mTs; }

private:
  std::shared_ptr<Memory> mChunk;
  const double mTs;
};

class Chunks {
public:
  Chunks(uint32_t maxQueue)
    : mQueue(maxQueue), mOffset(0), m(), cv()
  {}
  ~Chunks() {}

  uint8_t *curBuf() const {
    std::unique_lock<std::mutex> lk(m);
    return mCurChunk ? mCurChunk->buf() : nullptr;
  }
  uint32_t curBytes() const {
    std::unique_lock<std::mutex> lk(m);
    return mCurChunk ? mCurChunk->numBytes() : 0;
  }
  double curTs() const {
    std::unique_lock<std::mutex> lk(m);
    return mCurChunk ? mCurChunk->ts() : 0.0;
  }

  uint32_t curOffset() const {
    std::unique_lock<std::mutex> lk(m);
    return mOffset;
  }
  void incOffset(uint32_t off) {
    std::unique_lock<std::mutex> lk(m);
    mOffset += off;
  }

  void waitNext() {
    auto curChunk = mQueue.dequeue();

    std::unique_lock<std::mutex> lk(m);
    mCurChunk = curChunk;
    mOffset = 0;
    cv.notify_one();
  }

  void waitDone() {
    std::unique_lock<std::mutex> lk(m);
    while(mCurChunk) {
      cv.wait(lk);
    }
  }

  void push(std::shared_ptr<Chunk> chunk) {
    mQueue.enqueue(chunk);
  }

  void quit() {
    mQueue.quit();
  }

private:
  ChunkQueue<std::shared_ptr<Chunk> > mQueue;
  std::shared_ptr<Chunk> mCurChunk;
  uint32_t mOffset;
  mutable std::mutex m;
  std::condition_variable cv;
};

} // namespace streampunk

#endif
