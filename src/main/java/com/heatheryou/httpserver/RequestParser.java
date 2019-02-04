package com.heatheryou.httpserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RequestParser {
    private List<String> requestList;

    public List<String> readRequest(BufferedReader requestReader) throws IOException {
        requestList = new ArrayList<>();
        String inputLine = requestReader.readLine();
        while (inputLine != null && inputLine.length() > 0) {
            requestList.add(inputLine);
            inputLine = requestReader.readLine();
        }
        return requestList;
    }

    public ResponseBuilder parse(List<String> requestLineList) {
        String requestString = requestLineList.get(0);
        String[] requestArray = requestString.split(" ");
        String method = requestArray[0];
        String uri = requestArray[1];
        String protocol = requestArray[2];
        return new ResponseBuilder(method, uri, protocol);
    }
}
