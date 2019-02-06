package com.heatheryou.httpserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RequestParser {
    private List<String> requestList;

    public void processRequest(BufferedReader requestReader, Router router) throws IOException {
        List<String> requestList = readRequest(requestReader);
        String[] requestLine = parse(requestList);
        String uri = getUri(requestLine);
        String method = getMethod(requestLine);
        router.setRequest(uri, method);
    }

    public List<String> readRequest(BufferedReader requestReader) throws IOException {
        requestList = new ArrayList<>();
        String inputLine = requestReader.readLine();
        while (inputLine != null && inputLine.length() > 0) {
            requestList.add(inputLine);
            inputLine = requestReader.readLine();
        }
        return requestList;
    }

    public String[] parse(List<String> requestList) {
        String requestString = requestList.get(0);
        return requestString.split(" ");
    }

    public String getUri(String[] requestLine) {
        return requestLine[0];
    }

    public String getMethod(String[] requestLine) {
        return requestLine[1];
    }
}
