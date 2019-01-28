package com.heatheryou.httpserver;

import java.io.IOException;

public interface IServerSocketWrapper extends AutoCloseable {
    ISocketWrapper accept() throws IOException;

    void close();
}
