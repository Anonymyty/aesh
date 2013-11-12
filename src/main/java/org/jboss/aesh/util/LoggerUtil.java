/*
 * Copyright 2012 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.jboss.aesh.util;

import org.jboss.aesh.console.Config;
import org.jboss.aesh.console.settings.Settings;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.MemoryHandler;
import java.util.logging.SimpleFormatter;

/**
 * butt ugly logger util, but its simple and gets the job done (hopefully not too dangerous)
 *
 * @author <a href="mailto:stale.pedersen@jboss.org">Ståle W. Pedersen</a>
 */
public class LoggerUtil {

    private static Handler logHandler;
    /**
     *
     * @param name class
     * @return logger
     */
    public static synchronized Logger getLogger(String name) {
        if(logHandler == null) {
            try {
                File logFile = new File(Settings.getInstance().getLogFile());
                if(logFile.getParentFile() != null && !logFile.getParentFile().isDirectory()) {
                    if(!logFile.getParentFile().mkdirs()) {
                        //if creating dirs failed, just create a logger without a file handler
                        return Logger.getLogger(name);
                    }
                }
                else if(logFile.isDirectory()) {
                    Settings.getInstance().setLogFile(Settings.getInstance().getLogFile()+ Config.getPathSeparator()+"aesh.log");
                }
                logHandler = new FileHandler(Settings.getInstance().getLogFile());
                logHandler.setFormatter(new SimpleFormatter());
            }
            catch (IOException e) {
                //just use a memoryhandler
                logHandler = new MemoryHandler();
                logHandler.setFormatter(new SimpleFormatter());
            }
        }

        Logger log =  Logger.getLogger(name);
        log.setUseParentHandlers(false);
        log.addHandler(logHandler);

        return log;
    }
}
