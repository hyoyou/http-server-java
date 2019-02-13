package com.heatheryou.httpserver;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

public class RequestParserTest {
    @Test
    public void processRequestReturnsRequest() throws IOException {
        String request = "GET /simple_get HTTP/1.1";
        StringReader stringReader = new StringReader(request);
        BufferedReader bufferedReader = new BufferedReader(stringReader);
        RequestParser parser = new RequestParser();
        Request actual = parser.processRequest(bufferedReader);
        assertThat(actual, instanceOf(Request.class));
    }

    @Test
    public void getMethodReturnsMethodOfRequest() throws IOException {
        String request = "GET /simple_get HTTP/1.1";
        StringReader stringReader = new StringReader(request);
        BufferedReader bufferedReader = new BufferedReader(stringReader);
        RequestParser parser = new RequestParser();
        parser.processRequest(bufferedReader);
        String actual = parser.getMethod();
        String expected = "GET";
        assertEquals(expected, actual);
    }

    @Test
    public void getMethodReturnsUriOfRequest() throws IOException {
        String request = "GET /simple_get HTTP/1.1";
        StringReader stringReader = new StringReader(request);
        BufferedReader bufferedReader = new BufferedReader(stringReader);
        RequestParser parser = new RequestParser();
        parser.processRequest(bufferedReader);
        String actual = parser.getUri();
        String expected = "/simple_get";
        assertEquals(expected, actual);
    }
}