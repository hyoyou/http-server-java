package com.heatheryou.httpserver;

import com.heatheryou.httpserver.constants.CharacterSet;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Does this class need to be stateful?

public class RequestParser {
    private List<String> requestList;
    String uri;
    String method;
    String body;

    public Request processRequest(BufferedReader requestReader) throws IOException {
        List<String> requestList = readRequest(requestReader);
        String[] requestLine = parse(requestList);
        int contentLength = getContentLength(requestList);
        setRequest(requestReader, requestLine, contentLength);
        return getRequest();
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
            // Maybe extract to a constant?
            String contentHeader = "Content-Length: ";
            if (request.startsWith(contentHeader)) {
                contentLength = Integer.parseInt(request.substring(contentHeader.length()));
            }
            n++;
        }
        return contentLength;
    }

    private void setRequest(BufferedReader requestReader, String[] requestLine, int contentLength) throws IOException {
        setUri(requestLine);
        setMethod(requestLine);
        setBody(requestReader, contentLength);
    }

    private void setUri(String[] requestLine) {
        uri = requestLine[1];
    }

    private void setMethod(String[] requestLine) {
        method = requestLine[0];
    }

    private void setBody(BufferedReader requestReader, int contentLength) throws IOException {
        if (contentLength == 0) {
            body = CharacterSet.EMPTY;
        } else {
            char[] charArray = readBody(requestReader, contentLength);
            body = new String(charArray);
        }
    }

    private char[] readBody(BufferedReader requestReader, int contentLength) throws IOException {
        char[] charArray = new char[contentLength];
        requestReader.read(charArray, 0, contentLength);
        return charArray;
    }

    private Request getRequest() {
        String uri = getUri();
        String method = getMethod();
        String body = getBody();
        return new Request(uri, method, body);
    }

    // Are these methods used anywhere?  Or would these just come from the Request?
    public String getUri() { return uri; }

    public String getMethod() { return method; }

    public String getBody() { return body; }

}
