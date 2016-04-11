/*
 * Copyright (c) 2015, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

import jdk.test.lib.OutputAnalyzer;
import jdk.test.lib.dcmd.CommandExecutor;
import jdk.test.lib.dcmd.PidJcmdExecutor;
import jdk.test.lib.dcmd.MainClassJcmdExecutor;
import jdk.test.lib.dcmd.FileJcmdExecutor;
import jdk.test.lib.dcmd.JMXExecutor;

import org.testng.annotations.Test;

/*
 * @test
 * @summary Test of diagnostic command VM.version (tests all DCMD executors)
 * @library /testlibrary
 * @modules java.base/jdk.internal.misc
 *          java.compiler
 *          java.management
 *          jdk.jvmstat/sun.jvmstat.monitor
 * @ignore 8072440
 * @build jdk.test.lib.*
 * @build jdk.test.lib.dcmd.*
 * @run testng/othervm -XX:+UsePerfData VMVersionTest
 */
public class VMVersionTest {
    public void run(CommandExecutor executor) {
        OutputAnalyzer output = executor.execute("VM.version");
        output.shouldMatch(".*(?:HotSpot|OpenJDK).*VM.*");
    }

    @Test
    public void pid() {
        run(new PidJcmdExecutor());
    }

    @Test
    public void mainClass() {
        run(new MainClassJcmdExecutor());
    }

    @Test
    public void file() {
        run(new FileJcmdExecutor());
    }

    @Test
    public void jmx() {
        run(new JMXExecutor());
    }
}
