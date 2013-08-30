/*
 * Copyright 2012 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.jboss.aesh.cl.completer;

import org.jboss.aesh.parser.Parser;

import java.util.List;

/**
 * @author <a href="mailto:stale.pedersen@jboss.org">Ståle W. Pedersen</a>
 */
public class DefaultValueOptionCompleter implements OptionCompleter {

    private final List<String> defaultValues;

    public DefaultValueOptionCompleter(List<String> defaultValues) {
        this.defaultValues = defaultValues;
    }

    @Override
    public CompleterData complete(String completeValue) {
        CompleterData completerData = new CompleterData();
        if(completeValue == null || completeValue.length() == 0)
            completerData.addAllCompleterValues(defaultValues);
        else {
            for(String value : defaultValues) {
                if(value.startsWith(completeValue))
                    completerData.addCompleterValue(value);
            }
        }

        if(completerData.getCompleterValues().size() == 1 &&
                completerData.getCompleterValues().get(0).contains(" ")) {
            return new CompleterData(
                    Parser.switchSpacesToEscapedSpacesInWord(completerData.getCompleterValues().get(0)),
                    completerData.isAppendSpace());
        }
        else
            return completerData;
    }
}
