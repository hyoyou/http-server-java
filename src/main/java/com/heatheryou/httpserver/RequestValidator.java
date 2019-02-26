package com.heatheryou.httpserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RequestValidator {
    private ISystemOutput systemOutput;
    private List<String> requestList = new ArrayList<>();

    public RequestValidator(ISystemOutput systemOutput) { this.systemOutput = systemOutput; }

    public List<String> validateRequest(BufferedReader requestReader) {
        try {
            return routeValidRequest(requestReader);
        } catch (Exception e) {
            return routeInvalidRequest();
        }
    }

    private List<String> routeValidRequest(BufferedReader requestReader) throws IOException {
        String inputLine = requestReader.readLine();
        while (inputLine != null && inputLine.length() > 0) {
            requestList.add(inputLine);
            inputLine = requestReader.readLine();
        }
        return requestList;
    }

    // Will need to validate uri, method, body here.. then send that data to parser

    private List<String> routeInvalidRequest() {
        systemOutput.printErr("Exception: Bad Request");
        return requestList;
    }
}
