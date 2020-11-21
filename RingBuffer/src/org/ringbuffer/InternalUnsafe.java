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

package org.ringbuffer;

import org.ringbuffer.lang.Check;
import org.ringbuffer.lang.Invokable;
import org.ringbuffer.lang.Lang;
import org.ringbuffer.lang.Method;
import org.ringbuffer.system.Platform;
import org.ringbuffer.system.Version;

import java.lang.reflect.AccessibleObject;

public class InternalUnsafe {
    public static final jdk.internal.misc.Unsafe UNSAFE;

    public static void init() {
    }

    static {
        final Module from = Lang.JAVA_BASE_MODULE;
        final Module to = Lang.ORG_RINGBUFFER_MODULE;
        final String packageName = "jdk.internal.misc";
        if (!from.isOpen(packageName, to)) {
            // Code is duplicated so that we do not have to use reflection in the other case.
            final Class<?> clazz = Module.class;
            Method<?> implAddOpens = Invokable.ofMethod(clazz, "implAddOpens", String.class, clazz);

            long OVERRIDE;
            if (Version.current() == Version.JAVA_11) {
                OVERRIDE = SunUnsafe.objectFieldOffset(AccessibleObject.class, "override");
            } else if (Platform.current().is32Bit()) {
                OVERRIDE = 8L;
            } else {
                long offset = SunUnsafe.objectFieldOffset(OopsCompressed.class, "i");
                Check.notEqualTo(offset, 8L);
                if (offset == 12L) {
                    OVERRIDE = 12L;
                } else if (offset == 16L) {
                    OVERRIDE = 16L;
                } else {
                    throw new AssertionError();
                }
            }
            SunUnsafe.UNSAFE.putBoolean(implAddOpens.getExecutable(), OVERRIDE, true);

            implAddOpens.setTargetInstance(from);
            implAddOpens.call(packageName, to);
        }
        UNSAFE = jdk.internal.misc.Unsafe.getUnsafe();
    }

    public static class OopsCompressed {
        public static final boolean value;

        int i;

        // Duplicated in InternalUnsafe.<clinit>
        static {
            long offset = Lang.objectFieldOffset(OopsCompressed.class, "i");
            if (offset == 8L) {
                assert Platform.current().is32Bit();
                value = false;
            } else if (offset == 12L) {
                value = true;
            } else if (offset == 16L) {
                value = false;
            } else {
                throw new AssertionError();
            }
        }
    }
}
