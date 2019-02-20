package com.heatheryou.httpserver;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import static org.junit.Assert.*;

public class RequestParserTest {
    String requestLine;
    StringReader stringReader;
    BufferedReader bufferedReader;
    RequestParser parser;

    @Before
    public void setUp() {
        requestLine = "GET /simple_get HTTP/1.1";
        stringReader = new StringReader(requestLine);
        bufferedReader = new BufferedReader(stringReader);
        parser = new RequestParser();
    }

    @Test
    public void processRequestReturnsRequest() throws IOException {
        Request request = parser.processRequest(bufferedReader);
        String[] actual = new String[]{ request.getUri(), request.getMethod() };
        String[] expected = new String[]{"/simple_get", "GET"};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void getMethodReturnsMethodOfRequest() throws IOException {
        parser.processRequest(bufferedReader);
        String actual = parser.getMethod();
        String expected = "GET";
        assertEquals(expected, actual);
    }

    @Test
    public void getUriReturnsUriOfRequest() throws IOException {
        parser.processRequest(bufferedReader);
        String actual = parser.getUri();
        String expected = "/simple_get";
        assertEquals(expected, actual);
    }
}