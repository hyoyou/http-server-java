package com.heatheryou.httpserver.route.handler;

import com.heatheryou.httpserver.Response;

public interface IResponseBuilder {
    Response buildResponse(int statusCode, String[] entityHeaders, String content);
}
