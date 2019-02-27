package com.heatheryou.httpserver;

import java.io.IOException;

public interface IServerSocketWrapper {
    ISocketWrapper accept() throws IOException;

    void close() throws IOException;
}