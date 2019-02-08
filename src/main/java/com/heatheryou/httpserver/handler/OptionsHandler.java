package com.heatheryou.httpserver.handler;

import com.heatheryou.httpserver.Request;
import com.heatheryou.httpserver.Response;
import com.heatheryou.httpserver.ResponseBuilder;
import com.heatheryou.httpserver.Router;
import com.heatheryou.httpserver.constants.CharacterSet;
import com.heatheryou.httpserver.constants.EntityHeader;

import java.util.List;

import static com.heatheryou.httpserver.constants.CharacterSet.EMPTY;
import static com.heatheryou.httpserver.constants.StatusLine.STATUS_CODE_200;

public class OptionsHandler implements IHandler {
    private Router router;

    @Override
    public Response handle(Request request) {
        ResponseBuilder responseBuilder = new ResponseBuilder();
        String[] entityHeaders = new String[]{ getAllowedMethods(request), getContentLength() };

        return responseBuilder.buildResponse(STATUS_CODE_200, entityHeaders, EMPTY);
    }

    public String getAllowedMethods(Request request) {
        return EntityHeader.ALLOWED_METHODS + CharacterSet.SP + getAllowedMethodsList(request) + CharacterSet.CRLF;
    }

    public String getAllowedMethodsList(Request request) {
        router = new Router();
        List<String> allowedMethods = router.allowedMethods(request.getUri());
        return String.join(",", allowedMethods);
    }

    public String getContentLength() {
        return EntityHeader.CONTENT_LENGTH + CharacterSet.SP + EntityHeader.CONTENT_LENGTH_0 + CharacterSet.CRLF;
    }
}
