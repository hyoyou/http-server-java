package com.heatheryou.httpserver;

import com.heatheryou.httpserver.constants.CharacterSet;
import com.heatheryou.httpserver.constants.EntityHeader;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RequestParser {
    private List<String> requestList;
    private ISystemOutput systemOutput;

    public RequestParser(ISystemOutput systemOutput) {
        this.systemOutput = systemOutput;
    }

    public Request processRequest(BufferedReader requestReader) {
        try {
            return routeValidRequest(requestReader);
        } catch (Exception e) {
            return routeInvalidRequest();
        }
    }

    private Request routeValidRequest(BufferedReader requestReader) throws IOException {
        List<String> requestList = readRequest(requestReader);
        String[] requestLine = parse(requestList);
        int contentLength = getContentLength(requestList);
        return getRequest(requestReader, requestLine, contentLength);
    }

    private Request routeInvalidRequest() {
        systemOutput.printErr("Exception: Bad Request");
        return new Request(null, null, null);
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

    private int getContentLength(List<String> requestList) {
        int contentLength = 0;
        int n = 0;
        while (n < requestList.size()) {
            String request = requestList.get(n);
            String contentHeader = EntityHeader.CONTENT_LENGTH;
            if (request.startsWith(contentHeader)) {
                contentLength = Integer.parseInt(request.substring(contentHeader.length()));
            }
            n++;
        }
        return contentLength;
    }

    private Request getRequest(BufferedReader requestReader, String[] requestLine, int contentLength) throws IOException {
        String uri = extractUri(requestLine);
        String method = extractMethod(requestLine);
        String body = extractBody(requestReader, contentLength);
        return new Request(uri, method, body);
    }

    private String extractUri(String[] requestLine) {
        return requestLine[1];
    }

    private String extractMethod(String[] requestLine) {
        return requestLine[0];
    }

    private String extractBody(BufferedReader requestReader, int contentLength) throws IOException {
        if (contentLength == 0) {
            return CharacterSet.EMPTY;
        } else {
            char[] charArray = readBody(requestReader, contentLength);
            return new String(charArray);
        }
    }

    private char[] readBody(BufferedReader requestReader, int contentLength) throws IOException {
        char[] charArray = new char[contentLength];
        requestReader.read(charArray, 0, contentLength);
        return charArray;
    }
}