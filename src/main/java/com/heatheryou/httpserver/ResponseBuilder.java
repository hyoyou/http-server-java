package com.heatheryou.httpserver;

import com.heatheryou.httpserver.constants.EntityHeader;
import com.heatheryou.httpserver.constants.StatusLine;
import com.heatheryou.httpserver.constants.CharacterSet;

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
                StatusLine.HTTP_VERSION + CharacterSet.SP + StatusLine.STATUS_CODE_200 + CharacterSet.SP + StatusLine.REASON_PHRASE_200 + CharacterSet.CRLF +
                EntityHeader.CONTENT_LENGTH + CharacterSet.SP + 0 + CharacterSet.CRLF
            );
        } else {
            return "Invalid Method";
        }
    }

    public String setBody() {
        if (uri.equals("/simple_get")) {
            return CharacterSet.CRLF;
        } else {
            return "";
        }
    }
}
