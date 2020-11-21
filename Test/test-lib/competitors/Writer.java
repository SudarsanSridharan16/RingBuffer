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

package test.competitors;

import test.Profiler;
import test.TestThreadGroup;
import test.object.Event;

import java.util.Queue;

class Writer extends TestThread {
    static TestThreadGroup startGroupAsync(Queue<Event> queue, Profiler profiler) {
        TestThreadGroup group = new TestThreadGroup(numIterations -> new Writer(numIterations, queue));
        group.start(profiler);
        return group;
    }

    static void runGroupAsync(Queue<Event> queue, Profiler profiler) {
        startGroupAsync(queue, profiler).waitForCompletion(null);
    }

    static Writer startAsync(int numIterations, Queue<Event> queue, Profiler profiler) {
        Writer writer = new Writer(numIterations, queue);
        writer.startNow(profiler);
        return writer;
    }

    static void runAsync(int numIterations, Queue<Event> queue, Profiler profiler) {
        startAsync(numIterations, queue, profiler).waitForCompletion(null);
    }

    private Writer(int numIterations, Queue<Event> queue) {
        super(numIterations, queue);
    }

    @Override
    protected void loop() {
        Queue<Event> queue = getQueue();
        for (int numIterations = getNumIterations(); numIterations > 0; numIterations--) {
            queue.offer(new Event(numIterations));
        }
    }
}
