package com.heatheryou.httpserver;

import com.heatheryou.httpserver.route.RequestHandler;
import com.heatheryou.httpserver.route.Router;
import com.heatheryou.httpserver.route.handler.ResponseBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Server implements AutoCloseable {
    private IServerSocketWrapper serverSocketWrapper;
    private Router router;
    private RequestParser parser;

    public Server(IServerSocketWrapper serverSocketWrapper, Router router, RequestParser parser) {
        this.serverSocketWrapper = serverSocketWrapper;
        this.router = router;
        this.parser = parser;
    }

    public void start() throws IOException {
        ISocketWrapper clientSocket = serverSocketWrapper.accept();
        BufferedReader requestReader = clientSocket.getInputStreamReader();
        PrintWriter printWriter = clientSocket.getPrintWriter();
        Request request = parser.processRequest(requestReader);
        RequestHandler handler = router.handleRequest(request);
        Response response = handler.handle(request);
        String responseString = response.getResponse();
        printWriter.print(responseString);
        printWriter.flush();
    }

    @Override
    public void close() throws IOException {
        serverSocketWrapper.close();
    }
}