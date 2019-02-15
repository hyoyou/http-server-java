package com.heatheryou.httpserver.route.handler;

import com.heatheryou.httpserver.Request;
import com.heatheryou.httpserver.Response;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GetHandlerTest {
    @Test
    public void getHandlerReturnsStatusCode301IfUriIsRedirect() {
        BuildResponse buildResponse = new ResponseBuilder();
        GetHandler handler = new GetHandler(buildResponse);
        Request request = new Request("/redirect", "GET");
        Response response = handler.handle(request);
        String actual = response.getResponseLine();
        String expected = String.join("\r\n", new String[]{
                "HTTP/1.1 301 Moved Permanently",
                "Location: http://0.0.0.0:5000/simple_get",
                "Content-Length: 0",
                "",
                "",
                ""
        });
        assertEquals(expected, actual);
    }
}