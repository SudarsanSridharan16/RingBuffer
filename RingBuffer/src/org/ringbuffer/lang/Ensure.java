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

package org.ringbuffer.lang;

public class Ensure {
    public static void notEqual(int left, int right) {
        if (left == right) {
            throw new IllegalStateException();
        }
    }

    public static void notEqualTo(int left, int right) {
        if (left == right) {
            throw new IllegalIntStateException(left);
        }
    }

    public static void notEqual(long left, long right) {
        if (left == right) {
            throw new IllegalStateException();
        }
    }

    public static void notEqualTo(long left, long right) {
        if (left == right) {
            throw new IllegalLongStateException(left);
        }
    }

    public static void notEqual(Object left, Object right) {
        if (left.equals(right)) {
            throw new IllegalStateException();
        }
    }

    public static void notEqualTo(Object left, Object right) {
        if (left.equals(right)) {
            throw new IllegalObjectStateException(left);
        }
    }

    public static void lesser(int value, int cap) {
        if (value >= cap) {
            throw new IllegalIntStateException(value);
        }
    }

    public static void lesserThan(int value, int cap) {
        if (value >= cap) {
            throw new IllegalIntStateException(value, cap);
        }
    }

    public static void lesser(long value, long cap) {
        if (value >= cap) {
            throw new IllegalLongStateException(value);
        }
    }

    public static void lesserThan(long value, long cap) {
        if (value >= cap) {
            throw new IllegalLongStateException(value, cap);
        }
    }

    public static void notLesser(int value, int cap) {
        if (value < cap) {
            throw new IllegalIntStateException(value);
        }
    }

    public static void notLesserThan(int value, int cap) {
        if (value < cap) {
            throw new IllegalIntStateException(value, cap);
        }
    }

    public static void notLesser(long value, long cap) {
        if (value < cap) {
            throw new IllegalLongStateException(value);
        }
    }

    public static void notLesserThan(long value, long cap) {
        if (value < cap) {
            throw new IllegalLongStateException(value, cap);
        }
    }

    public static void greater(int value, int cap) {
        if (value <= cap) {
            throw new IllegalIntStateException(value);
        }
    }

    public static void greaterThan(int value, int cap) {
        if (value <= cap) {
            throw new IllegalIntStateException(value, cap);
        }
    }

    public static void greater(long value, long cap) {
        if (value <= cap) {
            throw new IllegalLongStateException(value);
        }
    }

    public static void greaterThan(long value, long cap) {
        if (value <= cap) {
            throw new IllegalLongStateException(value, cap);
        }
    }

    public static void notGreater(int value, int cap) {
        if (value > cap) {
            throw new IllegalIntStateException(value);
        }
    }

    public static void notGreaterThan(int value, int cap) {
        if (value > cap) {
            throw new IllegalIntStateException(value, cap);
        }
    }

    public static void notGreater(long value, long cap) {
        if (value > cap) {
            throw new IllegalLongStateException(value);
        }
    }

    public static void notGreaterThan(long value, long cap) {
        if (value > cap) {
            throw new IllegalLongStateException(value, cap);
        }
    }

    public static void positive(int value) {
        if (value <= 0) {
            throw new IllegalIntStateException(value);
        }
    }

    public static void positive(long value) {
        if (value <= 0L) {
            throw new IllegalLongStateException(value);
        }
    }

    public static void notPositive(int value) {
        if (value > 0) {
            throw new IllegalIntStateException(value);
        }
    }

    public static void notPositive(long value) {
        if (value > 0L) {
            throw new IllegalLongStateException(value);
        }
    }

    public static void negative(int value) {
        if (value >= 0) {
            throw new IllegalIntStateException(value);
        }
    }

    public static void negative(long value) {
        if (value >= 0L) {
            throw new IllegalLongStateException(value);
        }
    }

    public static void notNegative(int value) {
        if (value < 0) {
            throw new IllegalIntStateException(value);
        }
    }

    public static void notNegative(long value) {
        if (value < 0L) {
            throw new IllegalLongStateException(value);
        }
    }

    public static void notZero(int value) {
        if (value == 0) {
            throw new IllegalStateException();
        }
    }

    public static void notZero(long value) {
        if (value == 0L) {
            throw new IllegalStateException();
        }
    }

    public static void notNull(Object value) {
        if (value == null) {
            throw new IllegalStateException();
        }
    }

    private static final String exceptionClassName = IllegalStateException.class.getName() + ": ";

    private static class IllegalIntStateException extends IllegalStateException {
        private static final long serialVersionUID = 0L;

        IllegalIntStateException(int value) {
            super(exceptionClassName + value);
        }

        IllegalIntStateException(int value, int cap) {
            super(exceptionClassName + value + " " + cap);
        }

        @Override
        public String toString() {
            return getMessage();
        }
    }

    private static class IllegalLongStateException extends IllegalStateException {
        private static final long serialVersionUID = 0L;

        IllegalLongStateException(long value) {
            super(exceptionClassName + value);
        }

        IllegalLongStateException(long value, long cap) {
            super(exceptionClassName + value + " " + cap);
        }

        @Override
        public String toString() {
            return getMessage();
        }
    }

    private static class IllegalObjectStateException extends IllegalStateException {
        private static final long serialVersionUID = 0L;

        IllegalObjectStateException(Object value) {
            super(exceptionClassName + value);
        }

        @Override
        public String toString() {
            return getMessage();
        }
    }
}
