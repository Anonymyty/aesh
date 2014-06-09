/*
 * Copyright 2012 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.jboss.aesh.io;

import java.util.List;

/**
 * @author <a href="mailto:stale.pedersen@jboss.org">Ståle W. Pedersen</a>
 */
public interface FileResource {

    String getName();

    boolean isLeaf();

    boolean exists();

    boolean mkdir();

    List<FileResource> listFileResources();

    List<FileResource> listFileResources(FileResourceFilter filter);

    List<FileResource> listRoots();

    List<FileResource> resolve(FileResource incPath, FileResource cwd);


}
