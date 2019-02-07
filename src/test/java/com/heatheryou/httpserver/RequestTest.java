package com.heatheryou.httpserver;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RequestTest {
    @Test
    public void ItReturnsTheMethod() {
        Request request = new Request("/simple_get", "GET");
        String actual = request.getMethod();
        String expected = "GET";
        assertEquals(expected, actual);
    }

    @Test
    public void ItReturnsTheUri() {
        Request request = new Request("/simple_get", "GET");
        String actual = request.getUri();
        String expected = "/simple_get";
        assertEquals(expected, actual);
    }
}