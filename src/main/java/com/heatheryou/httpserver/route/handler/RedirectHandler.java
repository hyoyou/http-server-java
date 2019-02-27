package com.heatheryou.httpserver.route.handler;

import com.heatheryou.httpserver.Request;
import com.heatheryou.httpserver.Response;
import com.heatheryou.httpserver.constants.CharacterSet;
import com.heatheryou.httpserver.constants.EntityHeader;
import com.heatheryou.httpserver.route.RequestHandler;
import com.heatheryou.httpserver.route.handler.BuildResponse;

import static com.heatheryou.httpserver.constants.CharacterSet.EMPTY;
import static com.heatheryou.httpserver.constants.StatusLine.STATUS_CODE_301;

public class RedirectHandler implements RequestHandler {
    BuildResponse responseBuilder;

    public RedirectHandler(BuildResponse responseBuilder) { this.responseBuilder = responseBuilder; }

    @Override
    public Response handle(Request request) {
        String body = request.getBody();
        String[] entityHeaders = new String[]{ getLocation(), getContentLength(body) };

        return responseBuilder.buildResponse(STATUS_CODE_301, entityHeaders, EMPTY);
    }

    private String getLocation() {
        return EntityHeader.LOCATION + CharacterSet.SPACE + EntityHeader.SIMPLE_GET_URL + CharacterSet.CRLF;
    }

    private String getContentLength(String body) {
        return EntityHeader.CONTENT_LENGTH + body.length() + CharacterSet.CRLF;
    }
}
