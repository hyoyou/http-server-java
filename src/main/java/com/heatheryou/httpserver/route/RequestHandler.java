package com.heatheryou.httpserver.route;

import com.heatheryou.httpserver.Request;
import com.heatheryou.httpserver.Response;

public interface RequestHandler {
    Response handle(Request request);
}
