package com.heatheryou.httpserver;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class RequestParserTest {
    @Test
    public void ItReturnsRequestInListForm() throws IOException {
        String request = "GET /simple_get HTTP/1.1";
        StringReader stringReader = new StringReader(request);
        BufferedReader bufferedReader = new BufferedReader(stringReader);
        RequestParser parser = new RequestParser();
        List<String> actual = parser.readRequest(bufferedReader);
        List<String> expected = Arrays.asList("GET /simple_get HTTP/1.1");
        assertEquals(expected, actual);
    }

    @Test
    public void ItReturnsRequestLineGivenFullRequest() {
        RequestParser parser = new RequestParser();
        List<String> requestList = Arrays.asList("GET /simple_get HTTP/1.1");
        String[] actual = parser.parse(requestList);
        String[] expected = new String[3];
        expected[0] = "GET";
        expected[1] = "/simple_get";
        expected[2] = "HTTP/1.1";
        assertArrayEquals(expected, actual);
    }

    @Test
    public void ItReturnsUriGivenRequestLine() {
        RequestParser parser = new RequestParser();
        List<String> requestList = Arrays.asList("GET /simple_get HTTP/1.1");
        String[] requestLine = parser.parse(requestList);
        String actual = parser.getUri(requestLine);
        String expected = "GET";
        assertEquals(expected, actual);
    }

    @Test
    public void ItReturnsMethodGivenRequestLine() {
        RequestParser parser = new RequestParser();
        List<String> requestList = Arrays.asList("GET /simple_get HTTP/1.1");
        String[] requestLine = parser.parse(requestList);
        String actual = parser.getMethod(requestLine);
        String expected = "/simple_get";
        assertEquals(expected, actual);
    }
}