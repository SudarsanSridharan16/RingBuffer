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

package org.ringbuffer.wait;

import org.ringbuffer.concurrent.AtomicBoolean;
import org.ringbuffer.lang.Lang;

public class InterruptibleBusyWaitStrategy implements BusyWaitStrategy {
    private static final long INTERRUPTED = Lang.objectFieldOffset(InterruptibleBusyWaitStrategy.class, "interrupted");

    private final BusyWaitInterruptedException exception;
    private final BusyWaitStrategy nextStrategy;

    private boolean interrupted;

    public InterruptibleBusyWaitStrategy(boolean whileReading, BusyWaitStrategy nextStrategy) {
        this(BusyWaitInterruptedException.getInstance(whileReading), nextStrategy);
    }

    public InterruptibleBusyWaitStrategy(BusyWaitInterruptedException exception, BusyWaitStrategy nextStrategy) {
        this.exception = exception;
        this.nextStrategy = nextStrategy;
    }

    public void interrupt() {
        AtomicBoolean.setOpaque(this, INTERRUPTED, true);
    }

    @Override
    public void reset() {
        nextStrategy.reset();
    }

    @Override
    public void tick() {
        if (AtomicBoolean.getOpaque(this, INTERRUPTED)) {
            AtomicBoolean.setOpaque(this, INTERRUPTED, false);
            throw exception;
        }
        nextStrategy.tick();
    }
}
