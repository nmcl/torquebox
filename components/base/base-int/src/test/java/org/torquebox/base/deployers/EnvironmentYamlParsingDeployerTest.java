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

package org.torquebox.base.deployers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.net.URL;
import java.util.Map;

import org.jboss.deployers.structure.spi.DeploymentUnit;
import org.junit.Before;
import org.junit.Test;
import org.torquebox.base.metadata.RubyApplicationMetaData;
import org.torquebox.test.mc.vdf.AbstractDeployerTestCase;

public class EnvironmentYamlParsingDeployerTest extends AbstractDeployerTestCase {
	
    @Before
    public void setUp() throws Throwable {
        addDeployer( new TorqueBoxYamlParsingDeployer() );
        addDeployer( new EnvironmentYamlParsingDeployer() );
    }

    @Test
    public void testBooleanEnvironmentYml() throws Exception {
        URL environmentYml = getClass().getResource( "/sections-torquebox.yml" );

        String deploymentName = addDeployment( environmentYml, "torquebox.yml" );
        processDeployments( true );

        DeploymentUnit unit = getDeploymentUnit( deploymentName );
        assertNotNull( unit );

        RubyApplicationMetaData appMetaData = unit.getAttachment( RubyApplicationMetaData.class );
        assertNotNull( appMetaData );

        Map<String, String> environmentVariables = appMetaData.getEnvironmentVariables();
        assertNotNull( environmentVariables );
        
        String booleanVariable = environmentVariables.get("A_BOOLEAN_VALUE");
		assertEquals( "true", booleanVariable );
    }
}
