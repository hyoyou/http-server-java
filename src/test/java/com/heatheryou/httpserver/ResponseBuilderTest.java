package com.heatheryou.httpserver;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ResponseBuilderTest {
    @Test
    public void ResponseBuilderReturnsHeaderGivenCorrectMethod() {
        ResponseBuilder responseBuilder = new ResponseBuilder("GET", "/simple_get", "HTTP/1.1");
        String actual = responseBuilder.setHeader();
        String expected = String.join("\n", new String[]{
                "HTTP/1.1 200 OK",
                "Content-Length: 0",
                ""
        });
        assertEquals(expected, actual);
    }

    @Test
    public void ResponseBuilderReturnsInvalidMessageGivenIncorrectMethod() {
        ResponseBuilder responseBuilder = new ResponseBuilder("POST", "/simple_get", "HTTP/1.1");
        String actual = responseBuilder.setHeader();
        String expected = "Invalid Method";
        assertEquals(expected, actual);
    }

    @Test
    public void ResponseBuilderReturnsEmptyBodyWhenURIExpectsEmptyBody() {
        ResponseBuilder responseBuilder = new ResponseBuilder("GET", "/simple_get", "HTTP/1.1");
        String actual = responseBuilder.setBody();
        String expected = "\r\n";
        assertEquals(expected, actual);
    }
}