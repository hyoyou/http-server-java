package com.heatheryou.httpserver;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class RequestParserTest {
    String requestLine;
    StringReader stringReader;
    BufferedReader bufferedReader;
    List<String> requestData;
    RequestParser parser;
    MockSystemOutput systemOutput;

    @Before
    public void setUp() {
        requestLine = "GET /simple_get HTTP/1.1";
        stringReader = new StringReader(requestLine);
        bufferedReader = new BufferedReader(stringReader);
        requestData = new ArrayList<>();
        requestData.add("GET /simple_get HTTP/1.1");
        systemOutput = new MockSystemOutput();
        parser = new RequestParser();
    }

    @Test
    public void processRequestReturnsRequest() throws IOException {
        Request request = parser.processRequest(bufferedReader, requestData);
        String[] actual = new String[]{ request.getUri(), request.getMethod() };
        String[] expected = new String[]{"/simple_get", "GET"};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void getMethodReturnsMethodOfRequest() throws IOException {
        Request request = parser.processRequest(bufferedReader, requestData);
        String actual = request.getMethod();
        String expected = "GET";
        assertEquals(expected, actual);
    }

    @Test
    public void getUriReturnsUriOfRequest() throws IOException {
        Request request = parser.processRequest(bufferedReader, requestData);
        String actual = request.getUri();
        String expected = "/simple_get";
        assertEquals(expected, actual);
    }

    @Test
    public void getBodyReturnsBodyOfRequest() throws IOException {
        String request = "GET /simple_get HTTP/1.1\r\nContent-Length: 5\r\nhello\r\n";
        StringReader stringReader = new StringReader(request);
        BufferedReader bufferedReader = new BufferedReader(stringReader);
        systemOutput = new MockSystemOutput();
        parser = new RequestParser();
        Request parsedRequest = parser.processRequest(bufferedReader, requestData);
        String body = parsedRequest.getBody();
        int actual = body.length();
        int expected = 5;
        assertEquals(expected, actual);
    }
}