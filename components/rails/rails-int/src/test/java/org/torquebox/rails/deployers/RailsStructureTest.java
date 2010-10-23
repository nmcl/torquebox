package org.torquebox.rails.deployers;


import static org.junit.Assert.*;

import java.io.File;
import java.util.Map;

import org.jboss.deployers.spi.structure.StructureMetaData;
import org.jboss.deployers.structure.spi.DeploymentUnit;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.torquebox.rails.metadata.RailsApplicationMetaData;
import org.torquebox.test.mc.vdf.AbstractDeployerTestCase;

import org.junit.Before;
import org.junit.Test;

public class RailsStructureTest extends AbstractDeployerTestCase {

    private RailsStructure deployer;

    @Before
    public void setUpDeployer() throws Throwable {
        this.deployer = new RailsStructure();
        addStructureDeployer(this.deployer);
    }

    @Test
    public void testNotRailsArchive() throws Exception {
        JavaArchive archive = createJar("regular");

        archive.addDirectory( "/META-INF" );
        archive.addDirectory( "/classes" );

        File archiveFile = createJarFile(archive, ".jar");
        
        String deploymentName = addDeployment( archiveFile );
        processDeployments(true);
        
        DeploymentUnit unit = getDeploymentUnit( deploymentName );
        
        assertNotNull(unit);
        
        assertNotNull( unit.getAttachment( StructureMetaData.class ) );
        assertNull( unit.getAttachment( RailsApplicationMetaData.class ) );
    }
    
    @Test
    public void testRailsArchive() throws Exception {
        JavaArchive archive = createJar("someapp");
        
        archive.addResource(getClass().getResource("environment.rb"), "/config/environment.rb");
        
        File archiveFile = createJarFile(archive, ".rails" );
        String deploymentName = addDeployment( archiveFile );
        processDeployments(true);
        
        DeploymentUnit unit = getDeploymentUnit( deploymentName );
        
        assertNotNull(unit);
        
        assertNotNull( unit.getAttachment( StructureMetaData.class ) );
        assertNull( unit.getAttachment( RailsApplicationMetaData.class ) );
    }

}