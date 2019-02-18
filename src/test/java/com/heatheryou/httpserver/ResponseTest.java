package com.heatheryou.httpserver;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ResponseTest {
    @Test
    public void getResponseReturnsResponseLineProvidedHeaderAndBody() {
        Response response = new Response("HTTP/1.1 200 OK\r\nContent-Length: 0\r\n", "");
        String actual = response.getResponse();
        String expected = String.join("\r\n", new String[]{
                "HTTP/1.1 200 OK",
                "Content-Length: 0",
                "",
                ""
        });
        assertEquals(expected, actual);
    }
}