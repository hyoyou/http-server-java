package com.heatheryou.httpserver.route.handler;

import com.heatheryou.httpserver.Request;
import com.heatheryou.httpserver.Response;
import com.heatheryou.httpserver.constants.CharacterSet;
import com.heatheryou.httpserver.constants.EntityHeader;
import com.heatheryou.httpserver.route.RequestHandler;

import static com.heatheryou.httpserver.constants.CharacterSet.EMPTY;
import static com.heatheryou.httpserver.constants.StatusLine.STATUS_CODE_200;
import static com.heatheryou.httpserver.constants.StatusLine.STATUS_CODE_301;

public class GetHandler implements RequestHandler {
    BuildResponse responseBuilder;

    public GetHandler(BuildResponse responseBuilder) {
        this.responseBuilder = responseBuilder;
    }

    @Override
    public Response handle(Request request) {
        String body = request.getBody();
        String[] entityHeaders = new String[]{ getContentLength(body) };

        return responseBuilder.buildResponse(STATUS_CODE_200, entityHeaders, EMPTY);
    }

    private String getContentLength(String body) {
        return EntityHeader.CONTENT_LENGTH + body.length() + CharacterSet.CRLF;
    }
}