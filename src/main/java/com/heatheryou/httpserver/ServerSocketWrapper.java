package com.heatheryou.httpserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketWrapper implements IServerSocketWrapper, AutoCloseable {
    private ServerSocket serverSocket;

    public ServerSocketWrapper(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
    }

    @Override
    public ISocketWrapper accept() throws IOException {
        Socket socket = serverSocket.accept();
        return new SocketWrapper(socket);
    }

    @Override
    public void close() throws Exception {
        try {
            serverSocket.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
