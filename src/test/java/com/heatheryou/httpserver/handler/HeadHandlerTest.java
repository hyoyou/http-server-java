package com.heatheryou.httpserver.handler;

import com.heatheryou.httpserver.Request;
import com.heatheryou.httpserver.Response;
import com.heatheryou.httpserver.constants.CharacterSet;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class HeadHandlerTest {

    @Test
    public void ItReturnsTheStatusCode200AndAnEmptyBody() {
        HeadHandler handler = new HeadHandler();
        Request request = new Request("/simple_head", "HEAD");
        Response response = handler.handle(request);
        String actual = response.getResponseLine();
        String expected = String.join("\r\n", new String[]{
                "HTTP/1.1 200 OK",
                "Content-Length: 0",
                "",
                "",
                ""
        });
        assertEquals(expected, actual);
    }
}