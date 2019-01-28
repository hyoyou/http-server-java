package com.heatheryou.httpserver;

import java.io.StringReader;
import java.util.Arrays;
import java.util.List;

public class RequestParser {

    public ResponseBuilder parse(List<String> requestLineList) {
        String requestString = requestLineList.get(0);
        String[] requestArray = requestString.split(" ");
        String method = requestArray[0];
        String uri = requestArray[1];
        String protocol = requestArray[2];
        ResponseBuilder responseLine = new ResponseBuilder(method, uri, protocol);

        return responseLine;
    }
}
