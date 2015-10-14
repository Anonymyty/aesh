/*
 * Copyright (c) 2002-2015, the original author or authors.
 *
 * This software is distributable under the BSD license. See the terms of the
 * BSD license in the documentation provided with this software.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package org.jboss.aesh.terminal.impl;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.jboss.aesh.terminal.api.Attributes;
import org.jboss.aesh.terminal.api.Size;

public interface Pty extends Closeable {

    InputStream getMasterInput() throws IOException;

    OutputStream getMasterOutput() throws IOException;

    InputStream getSlaveInput() throws IOException;

    OutputStream getSlaveOutput() throws IOException;

    Attributes getAttr() throws IOException;

    void setAttr(Attributes attr) throws IOException;

    Size getSize() throws IOException;

    void setSize(Size size) throws IOException;

}
