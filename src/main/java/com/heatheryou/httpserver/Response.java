package com.heatheryou.httpserver;

import com.heatheryou.httpserver.constants.CharacterSet;

public class Response {
    String messageHeader;
    String body;

    public Response(String messageHeader, String body) {
        this.messageHeader = messageHeader;
        this.body = body;
    }

    public String getResponse() {
        return messageHeader + CharacterSet.CRLF + body;
    }
}