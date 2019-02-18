package com.heatheryou.httpserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RequestParser {
    private List<String> requestList;
    String method;
    String uri;
    String body;

    public Request processRequest(BufferedReader requestReader) throws IOException {
        List<String> requestList = readRequest(requestReader);
        String[] requestLine = parse(requestList);
        setUri(requestLine);
        setMethod(requestLine);
        String uri = getUri();
        String method = getMethod();
        String body = getBody();
        return new Request(uri, method, body);
    }

    private List<String> readRequest(BufferedReader requestReader) throws IOException {
        requestList = new ArrayList<>();
        String inputLine = requestReader.readLine();
        while (inputLine != null && inputLine.length() > 0) {
            requestList.add(inputLine);
            inputLine = requestReader.readLine();
        }
        return requestList;
    }

    private String[] parse(List<String> requestList) {
        String requestString = requestList.get(0);
        return requestString.split(" ");
    }

    private void setMethod(String[] requestLine) {
        method = requestLine[0];
    }

    private void setUri(String[] requestLine) {
        uri = requestLine[1];
    }

    public String getMethod() { return method; }

    public String getUri() { return uri; }

    public String getBody() { return body; }

}
