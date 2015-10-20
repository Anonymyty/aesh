/*
 * Copyright 2012 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.jboss.aesh.readline.actions;

import org.jboss.aesh.console.Config;
import org.jboss.aesh.console.InputProcessor;
import org.jboss.aesh.readline.Action;

/**
 * @author <a href="mailto:stale.pedersen@jboss.org">Ståle W. Pedersen</a>
 */
public class Interrupt implements Action {

    @Override
    public String name() {
        return "interrupt";
    }

    @Override
    public void apply(InputProcessor inputProcessor) {
        if(inputProcessor.getInterruptHook() != null) {
            inputProcessor.getBuffer().out().print(Config.getLineSeparator());
            inputProcessor.getInterruptHook().handleInterrupt(this);
        }
    }

}
