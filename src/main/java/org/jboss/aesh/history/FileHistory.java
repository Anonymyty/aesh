/*
 * Copyright 2012 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.jboss.aesh.history;

import org.jboss.aesh.console.Config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


/**
 * Read the history file at init and writeToStdOut to it at shutdown
 *
 * @author <a href="mailto:stale.pedersen@jboss.org">Ståle W. Pedersen</a>
 */
public class FileHistory extends InMemoryHistory {

    private String historyFile;

    public FileHistory(String fileName, int maxSize) throws IOException {
        super(maxSize);
        historyFile = fileName;
        readFile();
    }

    /**
     * Read specified history file to history buffer
     *
     * @throws IOException io
     */
    private void readFile() throws IOException {
        BufferedReader reader = null;
        try {
            if(new File(historyFile).exists()) {
                reader = new BufferedReader(new FileReader(historyFile));
                String line;
                while((line = reader.readLine()) != null)
                    push(line);
            }
        }
        finally {
            if(reader != null)
                reader.close();
        }
    }

    /**
     * Write the content of the history buffer to file
     *
     * @throws IOException io
     */
    private void writeFile() throws IOException {
        new File(historyFile).delete();

        FileWriter fw = new FileWriter(historyFile);

        for(int i=0; i < size();i++)
            fw.write(get(i) + (Config.getLineSeparator()));

        fw.flush();
        fw.close();
    }

    @Override
    public void stop() {
       try {
           writeFile();
       } catch (IOException e) {
           e.printStackTrace();
       }
    }

}
