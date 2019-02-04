package com.heatheryou.httpserver;

import com.heatheryou.httpserver.constants.StatusLine;

public class ResponseBuilder {
    String method;
    String uri;
    String protocol;

    public ResponseBuilder(String method, String uri, String protocol) {
        this.method = method;
        this.uri = uri;
        this.protocol = protocol;
    }

    public String setHeader() {
        if (method.equals("GET") || method.equals("HEAD")) {
            return(
                StatusLine.HTTP_VERSION + StatusLine.SP + StatusLine.STATUS_CODE_200 + StatusLine.SP + StatusLine.REASON_PHRASE_200 + StatusLine.CRLF +
                "Content-Length: 0\r\n"
            );
        } else {
            return "Invalid Method";
        }
    }

    public String setBody() {
        if (uri.equals("/simple_get")) {
            return "\r\n";
        } else {
            return "";
        }
    }
}
