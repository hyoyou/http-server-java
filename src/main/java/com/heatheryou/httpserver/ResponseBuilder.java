package com.heatheryou.httpserver;

import com.heatheryou.httpserver.constants.StatusLine;
import com.heatheryou.httpserver.constants.CharacterSet;

public class ResponseBuilder {
    String header;
    String body;
    String statusLine;
    String entityHeader;

    public Response buildResponse(int statusCode, String[] entityHeaders, String content) {
        setStatusLine(statusCode);
        setEntityHeader(entityHeaders);
        setBody(content);
        String header = getHeader();
        String body = getBody();
        return new Response(header, body);
    }

    public void setHeader() {
        header = getStatusLine() + getEntityHeader();
    }

    public String getHeader() {
        setHeader();
        return header;
    }

    public void setStatusLine(int statusCode) {
        statusLine = StatusLine.HTTP_VERSION + CharacterSet.SPACE + statusCode + CharacterSet.SPACE +
                StatusLine.getReasonPhrase(statusCode) + CharacterSet.CRLF;
    }

    public String getStatusLine() {
        return statusLine;
    }

    public void setEntityHeader(String[] entityHeaders) {
        entityHeader = String.join("", entityHeaders);
    }

    public String getEntityHeader() {
        return entityHeader;
    }

    public void setBody(String content) {
        body = content + CharacterSet.CRLF;
    }

    public String getBody() {
        return body;
    }
}
