package com.heatheryou.httpserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketWrapper implements ISocketWrapper {
    private Socket socket;

    public SocketWrapper(Socket socket) {
        this.socket = socket;
    }

    @Override
    public PrintWriter getPrintWriter() throws IOException {
        return new PrintWriter(socket.getOutputStream(), true);
    }

    @Override
    public BufferedReader getInputStreamReader() throws IOException {
        return new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }
}
