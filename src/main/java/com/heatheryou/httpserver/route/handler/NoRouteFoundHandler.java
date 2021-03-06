package com.heatheryou.httpserver.route.handler;

import com.heatheryou.httpserver.Request;
import com.heatheryou.httpserver.Response;
import com.heatheryou.httpserver.constants.CharacterSet;
import com.heatheryou.httpserver.constants.EntityHeader;
import com.heatheryou.httpserver.route.RequestHandler;

import static com.heatheryou.httpserver.constants.CharacterSet.EMPTY;
import static com.heatheryou.httpserver.constants.StatusLine.STATUS_CODE_404;

public class NoRouteFoundHandler implements RequestHandler {
    private BuildResponse responseBuilder;

    public NoRouteFoundHandler(BuildResponse buildResponse) {
        this.responseBuilder = buildResponse;
    }

    @Override
    public Response handle(Request request) {
        String[] entityHeaders = new String[]{ getContentLength() };

        return responseBuilder.buildResponse(STATUS_CODE_404, entityHeaders, EMPTY);
    }

    private String getContentLength() {
        return EntityHeader.CONTENT_LENGTH + EntityHeader.CONTENT_LENGTH_0 + CharacterSet.CRLF;
    }
}