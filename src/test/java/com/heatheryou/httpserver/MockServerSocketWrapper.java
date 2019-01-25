package com.heatheryou.httpserver;

import java.io.IOException;

public class MockServerSocketWrapper implements IServerSocketWrapper {
    private MockSocketWrapper mockSocketWrapper = new MockSocketWrapper();

    public MockServerSocketWrapper(MockSocketWrapper mockSocketWrapper) {
        this.mockSocketWrapper = mockSocketWrapper;
    }

    @Override
    public ISocketWrapper accept() throws IOException {
        return mockSocketWrapper;
    }
}
