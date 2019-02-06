package com.heatheryou.httpserver;

import com.heatheryou.httpserver.constants.EntityHeader;
import com.heatheryou.httpserver.constants.StatusLine;
import com.heatheryou.httpserver.constants.CharacterSet;

public class ResponseBuilder {
    String header;
    String body;

    public void setHeader(int statusCode) {
        header = (
                StatusLine.HTTP_VERSION + CharacterSet.SP + statusCode + CharacterSet.SP + StatusLine.getReasonPhrase(statusCode) + CharacterSet.CRLF +
                        EntityHeader.CONTENT_LENGTH + CharacterSet.SP + 0 + CharacterSet.CRLF
        );
    }

    public void setBody() {
       body = CharacterSet.CRLF;
    }

    public String getHeader() {
        return header;
    }

    public String getBody() {
        return body;
    }
}
