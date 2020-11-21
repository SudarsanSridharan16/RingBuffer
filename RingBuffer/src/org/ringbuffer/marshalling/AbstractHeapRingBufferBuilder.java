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

import java.util.Arrays;

abstract class AbstractHeapRingBufferBuilder<T> extends MarshallingRingBufferBuilder<T> {
    private final int capacity;
    // All fields are copied in <init>(AbstractHeapRingBufferBuilder<?>)

    AbstractHeapRingBufferBuilder(int capacity) {
        validateCapacity(capacity);
        validateCapacityPowerOfTwo(capacity);
        this.capacity = capacity;
    }

    AbstractHeapRingBufferBuilder(AbstractHeapRingBufferBuilder<?> builder) {
        super(builder);
        capacity = builder.capacity;
    }

    @Override
    protected void withoutLocks0() {
        super.withoutLocks0();
        validateCapacityPowerOfTwo(capacity);
    }

    int getCapacity() {
        return capacity;
    }

    int getCapacityMinusOne() {
        return capacity - 1;
    }

    byte[] getBuffer() {
        return HeapBuffer.allocate(capacity);
    }

    boolean[] getPositionNotModified() {
        boolean[] positionNotModified = new boolean[capacity];
        Arrays.fill(positionNotModified, true);
        return positionNotModified;
    }
}
