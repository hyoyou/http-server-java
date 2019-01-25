package com.heatheryou.httpserver;

import org.junit.Test;

import static org.junit.Assert.*;

public class ServerTest {
    @Test
    public void ServerListensForAConnectionRequestWhenStarted() {
        MockPrintWriter mockPrintWriter = new MockPrintWriter();
        MockSocketWrapper mockSocketWrapper = new MockSocketWrapper(mockPrintWriter);
        MockServerSocketWrapper mockServerSocketWrapper = new MockServerSocketWrapper(mockSocketWrapper);
        Server server = new Server(mockServerSocketWrapper);
        server.start();



        assertEquals(1, 2);
    }
}