package com.heatheryou.httpserver;

import com.heatheryou.httpserver.route.RequestHandler;
import com.heatheryou.httpserver.route.Router;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class Server {
    private IServerSocketWrapper serverSocketWrapper;
    private RequestValidator validator;
    private RequestParser parser;
    private Router router;

    public Server(IServerSocketWrapper serverSocketWrapper, RequestParser parser, RequestValidator validator, Router router) {
        this.serverSocketWrapper = serverSocketWrapper;
        this.validator = validator;
        this.parser = parser;
        this.router = router;
    }

    public void start() throws IOException {
        ISocketWrapper clientSocket = serverSocketWrapper.accept();
        BufferedReader requestReader = clientSocket.getInputStreamReader();
        PrintWriter printWriter = clientSocket.getPrintWriter();
        List<String> requestData = validator.validateRequest(requestReader);
        Request request = parser.processRequest(requestReader, requestData);
        RequestHandler handler = router.handleRequest(request);
        Response response = handler.handle(request);
        String responseString = response.getResponse();
        printWriter.print(responseString);
        printWriter.flush();
    }

    public void close() throws IOException {
        serverSocketWrapper.close();
    }
}