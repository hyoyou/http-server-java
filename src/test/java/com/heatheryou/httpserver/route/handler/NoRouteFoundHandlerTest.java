package com.heatheryou.httpserver.route.handler;

import com.heatheryou.httpserver.Request;
import com.heatheryou.httpserver.Response;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NoRouteFoundHandlerTest {
    @Test
    public void noRouteFoundHandlerReturnsStatusCode404AndAnEmptyBody() {
        BuildResponse buildResponse = new ResponseBuilder();
        NoRouteFoundHandler handler = new NoRouteFoundHandler(buildResponse);
        Request request = new Request("/invalid_route", "GET", null);
        Response response = handler.handle(request);
        String actual = response.getResponse();
        String expected = String.join("\r\n", new String[]{
                "HTTP/1.1 404 Not Found",
                "Content-Length: 0",
                "",
                ""
        });
        assertEquals(expected, actual);
    }
}