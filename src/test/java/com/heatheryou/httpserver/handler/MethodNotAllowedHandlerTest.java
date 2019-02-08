package com.heatheryou.httpserver.handler;

import com.heatheryou.httpserver.Request;
import com.heatheryou.httpserver.Response;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class MethodNotAllowedHandlerTest {

    @Test
    public void ItReturnsTheStatusCode405AndAnEmptyBody() {
        MethodNotAllowedHandler handler = new MethodNotAllowedHandler();
        Request request = new Request("/simple_get", "OPTIONS");
        Response response = handler.handle(request);
        String actual = response.getResponseLine();
        String expected = String.join("\r\n", new String[]{
                "HTTP/1.1 405 Method Not Allowed",
                "Allow: GET,HEAD",
                "Content-Length: 0",
                "",
                "",
                ""
        });
        assertEquals(expected, actual);
    }
}