package com.heatheryou.httpserver.handler;

import com.heatheryou.httpserver.Request;
import com.heatheryou.httpserver.Response;
import com.heatheryou.httpserver.ResponseBuilder;
import com.heatheryou.httpserver.constants.CharacterSet;
import com.heatheryou.httpserver.constants.EntityHeader;

import static com.heatheryou.httpserver.constants.CharacterSet.EMPTY;
import static com.heatheryou.httpserver.constants.StatusLine.STATUS_CODE_200;

public class GetHandler implements RequestHandler {
    @Override
    public Response handle(Request request) {
        ResponseBuilder responseBuilder = new ResponseBuilder();
        String[] entityHeaders = new String[]{ getContentLength() };

        return responseBuilder.buildResponse(STATUS_CODE_200, entityHeaders, EMPTY);
    }

    private String getContentLength() {
        return EntityHeader.CONTENT_LENGTH + CharacterSet.SPACE + EntityHeader.CONTENT_LENGTH_0 + CharacterSet.CRLF;
    }
}
