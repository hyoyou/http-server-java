package com.heatheryou.httpserver.route.handler;

import com.heatheryou.httpserver.Request;
import com.heatheryou.httpserver.Response;
import com.heatheryou.httpserver.ResponseBuilder;
import com.heatheryou.httpserver.constants.CharacterSet;
import com.heatheryou.httpserver.constants.EntityHeader;
import com.heatheryou.httpserver.route.RequestHandler;

import static com.heatheryou.httpserver.constants.CharacterSet.EMPTY;
import static com.heatheryou.httpserver.constants.StatusLine.STATUS_CODE_200;
import static com.heatheryou.httpserver.constants.StatusLine.STATUS_CODE_301;

public class GetHandler implements RequestHandler {
    @Override
    public Response handle(Request request) {
        ResponseBuilder responseBuilder = new ResponseBuilder();
        String[] entityHeaders = new String[]{ getContentLength() };

        String uri = request.getUri();
        if (uri.equals("/redirect")) {
            entityHeaders = new String[]{ getLocation(), getContentLength() };
            return responseBuilder.buildResponse(STATUS_CODE_301, entityHeaders, EMPTY);
        }
        return responseBuilder.buildResponse(STATUS_CODE_200, entityHeaders, EMPTY);
    }

    public String getLocation() {
        return EntityHeader.LOCATION + CharacterSet.SPACE + EntityHeader.SIMPLE_GET_URL + CharacterSet.CRLF;
    }

    private String getContentLength() {
        return EntityHeader.CONTENT_LENGTH + CharacterSet.SPACE + EntityHeader.CONTENT_LENGTH_0 + CharacterSet.CRLF;
    }
}