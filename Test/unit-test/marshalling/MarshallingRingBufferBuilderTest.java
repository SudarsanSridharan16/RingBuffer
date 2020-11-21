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

package org.ringbuffer.marshalling;

import org.junit.jupiter.api.Test;
import org.ringbuffer.RingBufferBuilderTest;
import test.marshalling.*;

class MarshallingRingBufferBuilderTest extends RingBufferBuilderTest {
    @Test
    void testClasses() {
        expectClass(ConcurrentHeapRingBuffer.class, ManyToManyHeapContentionTest.RING_BUFFER);
        expectClass(ConcurrentHeapBlockingRingBuffer.class, ManyToManyHeapBlockingContentionTest.Holder.RING_BUFFER, ManyToManyHeapBlockingContentionPerfTest.RING_BUFFER);
        expectClass(ConcurrentDirectRingBuffer.class, ManyToManyDirectContentionTest.RING_BUFFER);
        expectClass(ConcurrentDirectBlockingRingBuffer.class, ManyToManyDirectBlockingContentionTest.Holder.RING_BUFFER, ManyToManyDirectBlockingContentionPerfTest.RING_BUFFER);

        expectClass(AtomicReadHeapRingBuffer.class, ManyReadersHeapContentionTest.RING_BUFFER);
        expectClass(AtomicReadHeapBlockingRingBuffer.class, ManyReadersHeapBlockingContentionTest.Holder.RING_BUFFER, ManyReadersHeapBlockingContentionPerfTest.RING_BUFFER);
        expectClass(AtomicReadDirectRingBuffer.class, ManyReadersDirectContentionTest.RING_BUFFER);
        expectClass(AtomicReadDirectBlockingRingBuffer.class, ManyReadersDirectBlockingContentionTest.Holder.RING_BUFFER, ManyReadersDirectBlockingContentionPerfTest.RING_BUFFER);

        expectClass(AtomicWriteHeapRingBuffer.class, ManyWritersHeapContentionTest.RING_BUFFER);
        expectClass(AtomicWriteHeapBlockingRingBuffer.class, ManyWritersHeapBlockingContentionTest.Holder.RING_BUFFER, ManyWritersHeapBlockingContentionPerfTest.RING_BUFFER);
        expectClass(AtomicWriteDirectRingBuffer.class, ManyWritersDirectContentionTest.RING_BUFFER);
        expectClass(AtomicWriteDirectBlockingRingBuffer.class, ManyWritersDirectBlockingContentionTest.Holder.RING_BUFFER, ManyWritersDirectBlockingContentionPerfTest.RING_BUFFER);

        expectClass(VolatileHeapRingBuffer.class, OneToOneHeapContentionTest.RING_BUFFER);
        expectClass(VolatileHeapBlockingRingBuffer.class, OneToOneHeapBlockingContentionTest.Holder.RING_BUFFER, OneToOneHeapBlockingContentionPerfTest.RING_BUFFER);
        expectClass(VolatileDirectRingBuffer.class, OneToOneDirectContentionTest.RING_BUFFER);
        expectClass(VolatileDirectBlockingRingBuffer.class, OneToOneDirectBlockingContentionTest.Holder.RING_BUFFER, OneToOneDirectBlockingContentionPerfTest.RING_BUFFER);

        expectClass(FastConcurrentHeapRingBuffer.class, FastManyToManyHeapContentionTest.RING_BUFFER);
        expectClass(FastConcurrentDirectRingBuffer.class, FastManyToManyDirectContentionTest.RING_BUFFER);
        expectClass(FastAtomicReadHeapRingBuffer.class, FastManyReadersHeapContentionTest.RING_BUFFER);
        expectClass(FastAtomicReadDirectRingBuffer.class, FastManyReadersDirectContentionTest.RING_BUFFER);
        expectClass(FastAtomicWriteHeapRingBuffer.class, FastManyWritersHeapContentionTest.RING_BUFFER);
        expectClass(FastAtomicWriteDirectRingBuffer.class, FastManyWritersDirectContentionTest.RING_BUFFER);
        expectClass(FastVolatileHeapRingBuffer.class, FastOneToOneHeapContentionTest.RING_BUFFER);
        expectClass(FastVolatileDirectRingBuffer.class, FastOneToOneDirectContentionTest.RING_BUFFER);
    }
}
