package com.heatheryou.httpserver.route.handler;

import com.heatheryou.httpserver.Response;

// Why does this interface exist?
public interface BuildResponse {
    Response buildResponse(int statusCode, String[] entityHeaders, String content);
}
