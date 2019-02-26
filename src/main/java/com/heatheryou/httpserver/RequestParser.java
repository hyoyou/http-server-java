package com.heatheryou.httpserver;

import com.heatheryou.httpserver.constants.CharacterSet;
import com.heatheryou.httpserver.constants.EntityHeader;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class RequestParser {
    public Request processRequest(BufferedReader requestReader, List<String> requestData) throws IOException {
        String[] requestLine = parse(requestData);
        int contentLength = getContentLength(requestData);
        return getRequest(requestReader, requestLine, contentLength);
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