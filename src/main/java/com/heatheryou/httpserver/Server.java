package com.heatheryou.httpserver;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Server implements AutoCloseable {
    private IServerSocketWrapper serverSocketWrapper;

    public Server(IServerSocketWrapper serverSocketWrapper) {
        this.serverSocketWrapper = serverSocketWrapper;
    }

    public void start() {
         try {
             ISocketWrapper socketWrapper = serverSocketWrapper.accept();
             BufferedReader requestReader = socketWrapper.getInputStreamReader();
             RequestParser requestParser = new RequestParser();
             List<String> requestList = requestParser.readRequest(requestReader);
             ResponseBuilder requestLine = requestParser.parse(requestList);
             String header = requestLine.setHeader();
             String body = requestLine.setBody();
             Response response = new Response(header, body);
             String responseLine = response.getResponseLine();
             PrintWriter printWriter = socketWrapper.getPrintWriter();
             printWriter.print(responseLine);
             printWriter.flush();
         } catch (Exception e) {
             System.out.println(e.getMessage());
         }
    }

    @Override
    public void close() throws Exception {
    }
}