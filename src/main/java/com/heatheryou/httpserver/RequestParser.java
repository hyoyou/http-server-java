package com.heatheryou.httpserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RequestParser {
    private List<String> requestList;

    public Request processRequest(BufferedReader requestReader) throws IOException {
        List<String> requestList = readRequest(requestReader);
        String[] requestLine = parse(requestList);
        String uri = getUri(requestLine);
        String method = getMethod(requestLine);
        return new Request(uri, method);
    }

    public List<String> readRequest(BufferedReader requestReader) throws IOException {
        requestList = new ArrayList<>();
        String inputLine = requestReader.readLine();
        System.out.println("inputLine 1: " + inputLine);
        while (inputLine != null && inputLine.length() > 0) {
            requestList.add(inputLine);
            inputLine = requestReader.readLine();
            System.out.println("inputLine 2: " + inputLine);
        }
        return requestList;
    }

    public String[] parse(List<String> requestList) {
        System.out.println("Trying to parse..");
        String requestString = requestList.get(0);
        System.out.println("requestString: " + requestString);
        return requestString.split(" ");
    }

    public String getMethod(String[] requestLine) { return requestLine[0]; }

    public String getUri(String[] requestLine) {
        return requestLine[1];
    }

}
