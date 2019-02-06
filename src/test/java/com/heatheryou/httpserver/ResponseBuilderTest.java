package com.heatheryou.httpserver;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ResponseBuilderTest {
    @Test
    public void ItReturnsHeaderGivenCorrectMethod() {
        ResponseBuilder responseBuilder = new ResponseBuilder();
        String actual = responseBuilder.getHeader();
        String expected = String.join("\r\n", new String[]{
                "HTTP/1.1 200 OK",
                "Content-Length: 0",
                ""
        });
        assertEquals(expected, actual);
    }

    @Test
    public void ItReturnsInvalidMessageGivenIncorrectMethod() {
        ResponseBuilder responseBuilder = new ResponseBuilder();
        String actual = responseBuilder.getHeader();
        String expected = "Invalid Method";
        assertEquals(expected, actual);
    }

    @Test
    public void ItReturnsEmptyBodyWhenURIExpectsEmptyBody() {
        ResponseBuilder responseBuilder = new ResponseBuilder();
        String actual = responseBuilder.getBody();
        String expected = "\r\n";
        assertEquals(expected, actual);
    }
}