package com.heatheryou.httpserver.route.handler;

import com.heatheryou.httpserver.Request;
import com.heatheryou.httpserver.Response;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MethodNotAllowedHandlerTest {
    @Test
    public void methodNotAllowedHandlerReturnsStatusCode405AndAnEmptyBody() {
        BuildResponse buildResponse = new ResponseBuilder();
        List<String> allowedMethods = new ArrayList<>(Arrays.asList("GET","HEAD"));
        MethodNotAllowedHandler handler = new MethodNotAllowedHandler(buildResponse, allowedMethods);
        Request request = new Request("/simple_get", "OPTIONS", null);
        Response response = handler.handle(request);
        String actual = response.getResponse();
        String expected = String.join("\r\n", new String[]{
                "HTTP/1.1 405 Method Not Allowed",
                "Allow: GET,HEAD",
                "Content-Length: 0",
                "",
                ""
        });
        assertEquals(expected, actual);
    }
}