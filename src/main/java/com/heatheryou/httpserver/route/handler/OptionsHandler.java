package com.heatheryou.httpserver.route.handler;

import com.heatheryou.httpserver.Request;
import com.heatheryou.httpserver.Response;
import com.heatheryou.httpserver.constants.CharacterSet;
import com.heatheryou.httpserver.constants.EntityHeader;
import com.heatheryou.httpserver.route.RequestHandler;

import java.util.List;

import static com.heatheryou.httpserver.constants.CharacterSet.EMPTY;
import static com.heatheryou.httpserver.constants.StatusLine.STATUS_CODE_200;

public class OptionsHandler implements RequestHandler {
    private BuildResponse responseBuilder;
    private List<String> allowedMethods;

    public OptionsHandler(BuildResponse buildResponse, List<String> allowedMethods) {
        this.responseBuilder = buildResponse;
        this.allowedMethods = allowedMethods;
    }

    @Override
    public Response handle(Request request) {
        String[] entityHeaders = new String[]{ getAllowedMethods(), getContentLength() };

        return responseBuilder.buildResponse(STATUS_CODE_200, entityHeaders, EMPTY);
    }

    private String getAllowedMethods() {
        return EntityHeader.ALLOWED_METHODS + CharacterSet.SPACE + getAllowedMethodsList() + CharacterSet.CRLF;
    }

    private String getAllowedMethodsList() {
        return String.join(",", allowedMethods);
    }

    private String getContentLength() {
        return EntityHeader.CONTENT_LENGTH + EntityHeader.CONTENT_LENGTH_0 + CharacterSet.CRLF;
    }
}
