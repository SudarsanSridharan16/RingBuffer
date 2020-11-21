/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package test.object;

import org.ringbuffer.lang.Numbers;
import org.ringbuffer.object.ObjectRingBuffer;
import test.Profiler;
import test.TestThreadGroup;

class BatchReader extends Reader {
    static long runGroupAsync(int batchSize, ObjectRingBuffer<Event> ringBuffer, Profiler profiler) {
        TestThreadGroup group = new TestThreadGroup(numIterations -> new BatchReader(numIterations, batchSize, ringBuffer));
        group.start(null);
        group.waitForCompletion(profiler);
        return group.getReaderSum();
    }

    static long runAsync(int numIterations, int batchSize, ObjectRingBuffer<Event> ringBuffer, Profiler profiler) {
        BatchReader reader = new BatchReader(numIterations, batchSize, ringBuffer);
        reader.startNow(null);
        reader.waitForCompletion(profiler);
        return reader.getSum();
    }

    final int batchSize;

    BatchReader(int numIterations, int batchSize, ObjectRingBuffer<Event> ringBuffer) {
        super(Numbers.ceilDiv(numIterations, batchSize), ringBuffer);
        this.batchSize = batchSize;
    }

    @Override
    long collect() {
        ObjectRingBuffer<Event> ringBuffer = getObjectRingBuffer();
        int batchSize = this.batchSize;
        long sum = 0L;
        for (int numIterations = getNumIterations(); numIterations > 0; numIterations--) {
            ringBuffer.takeBatch(batchSize);
            for (int i = batchSize; i > 0; i--) {
                sum += ringBuffer.takePlain().getData();
            }
        }
        return sum;
    }
}
