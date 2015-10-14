/*
 * Copyright (c) 2002-2015, the original author or authors.
 *
 * This software is distributable under the BSD license. See the terms of the
 * BSD license in the documentation provided with this software.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package org.jboss.aesh.terminal.impl;


import org.jboss.aesh.terminal.api.Console.Signal;
import org.jboss.aesh.terminal.api.Console.SignalHandler;

public final class NativeSignalHandler implements SignalHandler {

    public static final NativeSignalHandler SIG_DFL = new NativeSignalHandler();

    public static final NativeSignalHandler SIG_IGN = new NativeSignalHandler();

    private NativeSignalHandler() {
    }

    public void handle(Signal signal) {
        throw new UnsupportedOperationException();
    }
}
