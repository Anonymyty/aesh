/*
 * Copyright 2012 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.jboss.aesh.io.filter;

import org.jboss.aesh.io.Resource;

/**
 * @author <a href="mailto:stale.pedersen@jboss.org">Ståle W. Pedersen</a>
 */
public class LeafResourceFilter implements ResourceFilter {

    @Override
    public boolean accept(Resource path) {
        return path.isLeaf();
    }
}
