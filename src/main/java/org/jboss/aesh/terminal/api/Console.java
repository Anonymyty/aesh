/*
 * Copyright (c) 2002-2015, the original author or authors.
 *
 * This software is distributable under the BSD license. See the terms of the
 * BSD license in the documentation provided with this software.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package org.jboss.aesh.terminal.api;

import java.io.Closeable;
import java.io.Flushable;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;

import org.jboss.aesh.terminal.impl.NativeSignalHandler;
import org.jboss.aesh.terminal.utils.InfoCmp.Capability;


public interface Console extends Closeable, Flushable {

    String getName();

    //
    // Signal support
    //

    enum Signal {
        INT,
        QUIT,
        TSTP,
        CONT,
        INFO,
        WINCH
    }

    interface SignalHandler {

        SignalHandler SIG_DFL = NativeSignalHandler.SIG_DFL;
        SignalHandler SIG_IGN = NativeSignalHandler.SIG_IGN;

        void handle(Signal signal);
    }

    SignalHandler handle(Signal signal, SignalHandler handler);

    void raise(Signal signal);

    //
    // Input / output
    //

    Reader reader();

    PrintWriter writer();

    InputStream input();

    OutputStream output();

    //
    // Pty settings
    //

    Attributes enterRawMode();

    boolean echo();

    boolean echo(boolean echo);

    Attributes getAttributes();

    void setAttributes(Attributes attr);

    Size getSize();

    void setSize(Size size);

    default int getWidth() {
        return getSize().getWidth();
    }

    default int getHeight() {
        return getSize().getHeight();
    }

    void flush();

    //
    // Infocmp capabilities
    //

    String getType();

    boolean puts(Capability capability, Object... params);

    boolean getBooleanCapability(Capability capability);

    Integer getNumericCapability(Capability capability);

    String getStringCapability(Capability capability);

}
