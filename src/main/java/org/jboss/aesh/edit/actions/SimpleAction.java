/*
 * JBoss, Home of Professional Open Source
 * Copyright 2014 Red Hat Inc. and/or its affiliates and other contributors
 * as indicated by the @authors tag. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.aesh.edit.actions;

/**
 * A placeholder class for simple actions that require little logic.
 * Typical movement one char back/forth, to the end/beginning of buffer.
 *
 * @author Ståle W. Pedersen <stale.pedersen@jboss.org>
 */
public class SimpleAction extends EditAction {

       public SimpleAction(int start, Action action) {
        super(start, action);
    }

    public SimpleAction(int start, Action action, int end) {
        super(start, action);
        setEnd(end);
    }

    @Override
    public void doAction(String buffer) {
        if(buffer.length() < getEnd())
            setEnd(buffer.length());

        if(getEnd() < 0)
            setEnd(0);
    }

}
