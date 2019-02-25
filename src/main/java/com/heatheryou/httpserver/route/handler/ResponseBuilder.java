package com.heatheryou.httpserver.route.handler;

import com.heatheryou.httpserver.Response;
import com.heatheryou.httpserver.constants.StatusLine;
import com.heatheryou.httpserver.constants.CharacterSet;

public class ResponseBuilder implements BuildResponse {
    String header;
    String body;
    String statusLine;
    String entityHeader;

    @Override
    public Response buildResponse(int statusCode, String[] entityHeaders, String content) {
        setResponse(statusCode, entityHeaders, content);
        return getResponse();
    }

    private void setResponse(int statusCode, String[] entityHeaders, String content) {
        setStatusLine(statusCode);
        setEntityHeader(entityHeaders);
        setHeader();
        setBody(content);
    }

    private Response getResponse() {
        String header = getHeader();
        String body = getBody();
        return new Response(header, body);
    }

    private void setStatusLine(int statusCode) {
        statusLine = StatusLine.HTTP_VERSION + CharacterSet.SPACE + statusCode + CharacterSet.SPACE +
                StatusLine.getReasonPhrase(statusCode) + CharacterSet.CRLF;
    }

    private void setEntityHeader(String[] entityHeaders) { entityHeader = String.join("", entityHeaders); }

    private void setHeader() { header = getStatusLine() + getEntityHeader(); }

    private void setBody(String content) { body = content; }

    public String getStatusLine() { return statusLine; }

    public String getEntityHeader() { return entityHeader; }

    public String getHeader() { return header; }

    public String getBody() { return body; }
}