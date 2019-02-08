package com.heatheryou.httpserver;

import com.heatheryou.httpserver.constants.CharacterSet;

public class Response {
    String header;
    String body;

    public Response(String header, String body) {
        this.header = header;
        this.body = body;
    }

    public String getResponseLine() {
        return header + body + CharacterSet.CRLF;
    }
}
