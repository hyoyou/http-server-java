package com.heatheryou.httpserver;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RequestTest {
    @Test
    public void getMethodReturnsTheMethod() {
        Request request = new Request("/simple_get", "GET");
        String actual = request.getMethod();
        String expected = "GET";
        assertEquals(expected, actual);
    }

    @Test
    public void getUriReturnsTheUri() {
        Request request = new Request("/simple_get", "GET");
        String actual = request.getUri();
        String expected = "/simple_get";
        assertEquals(expected, actual);
    }

    @Test
    public void getBodyReturnsTheBody() {
        Request request = new Request("/simple_get", "GET", "test body");
        String actual = request.getBody();
        String expected = "test body";
        assertEquals(expected, actual);
    }
}