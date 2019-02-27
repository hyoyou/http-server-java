package com.heatheryou.httpserver.route.handler;

import com.heatheryou.httpserver.Request;
import com.heatheryou.httpserver.Response;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RedirectHandlerTest {
    @Test
    public void redirectHandlerReturnsStatusCode301IfUriIsRedirect() {
        BuildResponse buildResponse = new ResponseBuilder();
        RedirectHandler handler = new RedirectHandler(buildResponse);
        Request request = new Request("/redirect", "GET", "");
        Response response = handler.handle(request);
        String actual = response.getResponse();
        String expected = String.join("\r\n", new String[]{
                "HTTP/1.1 301 Moved Permanently",
                "Location: http://0.0.0.0:5000/simple_get",
                "Content-Length: 0",
                "",
                ""
        });
        assertEquals(expected, actual);
    }
}