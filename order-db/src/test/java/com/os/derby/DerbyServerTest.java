package com.os.derby;

import static org.assertj.core.api.Assertions.*;
import org.junit.Test;

public class DerbyServerTest {

    @Test
    public void testPing() throws Exception{
        DerbyServer server = new DerbyServer.DerbyServerBuilder().propertiesFile("test-derby.properties").build();
        boolean isRunning = server.testForConnection();
        if(!isRunning){
            server.start();
        }
        assertThat(server.testForConnection()).isTrue();
    }

    @Test
    public void testShutdown() throws Exception{
        DerbyServer server = new DerbyServer.DerbyServerBuilder().propertiesFile("test-derby.properties").build();
        server.shutdown();
    }
}
