package com.heatheryou.httpserver.route.handler;

import com.heatheryou.httpserver.Request;
import com.heatheryou.httpserver.Response;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PostHandlerTest {
    @Test
    public void postHandlerReturnsBodyOfRequestInResponse() {
        BuildResponse buildResponse = new ResponseBuilder();
        OptionsHandler handler = new OptionsHandler(buildResponse);
        Request request = new Request("/echo_body", "POST", "some body");
        Response response = handler.handle(request);
        String actual = response.getResponseLine();
        String expected = String.join("\r\n", new String[]{
                "HTTP/1.1 200 OK",
                "Content-Length: 9",
                "",
                "some body"
        });
        assertEquals(expected, actual);
    }
}