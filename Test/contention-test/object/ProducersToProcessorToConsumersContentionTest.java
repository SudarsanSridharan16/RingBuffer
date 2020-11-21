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

import org.ringbuffer.object.PrefilledRingBuffer;
import org.ringbuffer.object.RingBuffer;
import test.Profiler;

public class ProducersToProcessorToConsumersContentionTest extends RingBufferTest {
    public static final RingBuffer<Event> PRODUCERS_RING_BUFFER =
            RingBuffer.<Event>withCapacity(FAST_NOT_ONE_TO_ONE_SIZE)
                    .manyWriters()
                    .oneReader()
                    .withoutLocks()
                    .build();
    public static final PrefilledRingBuffer<Event> CONSUMERS_RING_BUFFER =
            PrefilledRingBuffer.<Event>withCapacity(FAST_NOT_ONE_TO_ONE_SIZE)
                    .fillWith(FILLER)
                    .oneWriter()
                    .manyReaders()
                    .withoutLocks()
                    .build();

    public static void main(String[] args) {
        new ProducersToProcessorToConsumersContentionTest().runBenchmark();
    }

    @Override
    protected long getSum() {
        return MANY_WRITERS_SUM;
    }

    @Override
    protected long testSum() {
        Profiler profiler = createThroughputProfiler(TOTAL_ELEMENTS);
        Writer.startGroupAsync(PRODUCERS_RING_BUFFER, profiler);
        Processor.startAsync(TOTAL_ELEMENTS, PRODUCERS_RING_BUFFER);
        return Reader.runGroupAsync(CONSUMERS_RING_BUFFER, profiler);
    }
}
