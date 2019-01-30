package com.heatheryou.httpserver;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ResponseTest {
    @Test
    public void GetResponseLineReturnsResponseLineProvidedHeaderAndBody() {
        Response response = new Response("HTTP/1.1 200 OK\nContent-Length: 0\n", "\r\n");
        String actual = response.getResponseLine();
        String expected = String.join("\n", new String[]{
                "HTTP/1.1 200 OK",
                "Content-Length: 0",
                "\r\n"
        });
        assertEquals(expected, actual);
    }
}