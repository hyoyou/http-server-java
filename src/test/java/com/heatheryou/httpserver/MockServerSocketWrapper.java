package com.heatheryou.httpserver;

import java.io.IOException;

public class MockServerSocketWrapper implements IServerSocketWrapper {
    private MockSocketWrapper mockSocketWrapper;

    public MockServerSocketWrapper(MockSocketWrapper mockSocketWrapper) {
        this.mockSocketWrapper = mockSocketWrapper;
    }

    @Override
    public ISocketWrapper accept() {
        return mockSocketWrapper;
    }
}
