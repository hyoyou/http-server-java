package com.heatheryou.httpserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Server implements AutoCloseable {
    private IServerSocketWrapper serverSocketWrapper;

    public Server(IServerSocketWrapper serverSocketWrapper) {
        this.serverSocketWrapper = serverSocketWrapper;
    }

    public void start() throws IOException {
        try {
            while (true) {
                ISocketWrapper socketWrapper = serverSocketWrapper.accept();
                BufferedReader requestReader = socketWrapper.getInputStreamReader();
                List<String> requestLineList = new ArrayList<>();
                String inputLine = requestReader.readLine();
                while (inputLine.length() > 0) {
                    requestLineList.add(inputLine);
                    inputLine = requestReader.readLine();
                }
                RequestParser requestParser = new RequestParser();
                ResponseBuilder responseBuilder = requestParser.parse(requestLineList);
                String header = responseBuilder.setHeader();
                String body = responseBuilder.setBody();
                Response response = new Response(header, body);
                String responseLine = response.getResponseLine();
                PrintWriter printWriter = socketWrapper.getPrintWriter();
                printWriter.println(responseLine);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void close() throws Exception {

    }
}
