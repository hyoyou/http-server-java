package com.heatheryou.httpserver.route.handler;

import com.heatheryou.httpserver.Request;
import com.heatheryou.httpserver.Response;
import com.heatheryou.httpserver.route.Router;
import com.heatheryou.httpserver.constants.CharacterSet;
import com.heatheryou.httpserver.constants.EntityHeader;
import com.heatheryou.httpserver.route.RequestHandler;

import java.util.List;

import static com.heatheryou.httpserver.constants.CharacterSet.EMPTY;
import static com.heatheryou.httpserver.constants.StatusLine.STATUS_CODE_405;

public class MethodNotAllowedHandler implements RequestHandler {
    private Router router;

    @Override
    public Response handle(Request request) {
        ResponseBuilder responseBuilder = new ResponseBuilder();
        String[] entityHeaders = new String[]{ getAllowedMethods(request), getContentLength() };

        return responseBuilder.buildResponse(STATUS_CODE_405, entityHeaders, EMPTY);
    }

    private String getAllowedMethods(Request request) {
        return EntityHeader.ALLOWED_METHODS + CharacterSet.SPACE + getAllowedMethodsList(request) + CharacterSet.CRLF;
    }

    private String getAllowedMethodsList(Request request) {
        router = new Router();
        List<String> allowedMethods = router.allowedMethods(request.getUri());
        return String.join(",", allowedMethods);
    }

    private String getContentLength() {
        return EntityHeader.CONTENT_LENGTH + CharacterSet.SPACE + EntityHeader.CONTENT_LENGTH_0 + CharacterSet.CRLF;
    }
}

