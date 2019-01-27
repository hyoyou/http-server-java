package com.heatheryou.httpserver;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketWrapper implements ISocketWrapper {
    private Socket socket;

    public SocketWrapper(Socket socket) {
        this.socket = socket;
    }

    @Override
    public PrintWriter getPrintWriter() throws IOException {
        // The parameter to PrintWriter is untested.  =(
        return new PrintWriter(socket.getOutputStream(), true);
    }
}
