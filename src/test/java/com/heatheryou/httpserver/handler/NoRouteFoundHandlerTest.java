package com.heatheryou.httpserver.handler;

import com.heatheryou.httpserver.Request;
import com.heatheryou.httpserver.Response;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NoRouteFoundHandlerTest {

    @Test
    public void ItReturnsTheStatusCode404AndAnEmptyBody() {
        NoRouteFoundHandler handler = new NoRouteFoundHandler();
        Request request = new Request("/invalid_route", "GET");
        Response response = handler.handle(request);
        String actual = response.getResponseLine();
        String expected = String.join("\r\n", new String[]{
                "HTTP/1.1 404 Not Found",
                "Content-Length: 0",
                "",
                "",
                ""
        });
        assertEquals(expected, actual);
    }
}