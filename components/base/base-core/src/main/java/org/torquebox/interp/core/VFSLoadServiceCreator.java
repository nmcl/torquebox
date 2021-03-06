/*
 * Copyright 2008-2011 Red Hat, Inc, and individual contributors.
 * 
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 * 
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.torquebox.interp.core;

import org.jruby.Ruby;
import org.jruby.RubyInstanceConfig.LoadServiceCreator;
import org.jruby.runtime.load.LoadService;

/**
 * Factory for {@link VFSLoadService}.
 * 
 * @see VFSLoadService
 * 
 * @author Bob McWhirter <bmcwhirt@redhat.com>
 */
public class VFSLoadServiceCreator implements LoadServiceCreator {

    @Override
    public LoadService create(Ruby ruby) {
        return new VFSLoadService( ruby );
    }

}
