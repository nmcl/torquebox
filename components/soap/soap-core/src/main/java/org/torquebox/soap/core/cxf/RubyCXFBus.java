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

package org.torquebox.soap.core.cxf;

import org.apache.cxf.BusException;
import org.apache.cxf.bus.extension.ExtensionManagerBus;
import org.apache.cxf.transport.DestinationFactory;
import org.apache.cxf.transport.DestinationFactoryManager;
import org.apache.cxf.transport.servlet.ServletTransportFactory;

public class RubyCXFBus extends ExtensionManagerBus {
	
	public RubyCXFBus() {
		
	}
	
	public void start() throws BusException {
        DestinationFactoryManager dfm = getExtension(DestinationFactoryManager.class );
        DestinationFactory destinationFactory = new ServletTransportFactory( this );
        for ( String transportId : destinationFactory.getTransportIds() ) {
        	dfm.registerDestinationFactory( transportId,  destinationFactory );
        }
	}

}
