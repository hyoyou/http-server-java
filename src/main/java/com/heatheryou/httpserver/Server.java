package com.heatheryou.httpserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private IServerSocketWrapper serverSocketWrapper;

    public Server(IServerSocketWrapper serverSocketWrapper) {
        this.serverSocketWrapper = serverSocketWrapper;
    }

    public void start() throws IOException {
        ISocketWrapper socketWrapper = serverSocketWrapper.accept();
        PrintWriter printWriter = socketWrapper.getPrintWriter();
        printWriter.println("HTTP/1.1 200 OK");
        printWriter.println("Content-Length: 0");
        printWriter.println("");
    }
}
