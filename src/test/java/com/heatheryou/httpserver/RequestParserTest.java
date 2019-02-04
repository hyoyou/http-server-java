package com.heatheryou.httpserver;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class RequestParserTest {
    @Test
    public void ItReturnsRequestInListForm() throws IOException {
        String requestLine = "GET /simple_get HTTP/1.1";
        StringReader stringReader = new StringReader(requestLine);
        BufferedReader bufferedReader = new BufferedReader(stringReader);
        RequestParser parser = new RequestParser();
        List<String> actual = parser.readRequest(bufferedReader);
        List<String> expected = Arrays.asList("GET /simple_get HTTP/1.1");
        assertEquals(expected, actual);
    }

    @Test
    public void ItReturnsResponseLineGivenRequestLine() {
        RequestParser parser = new RequestParser();
        List<String> requestLineList = Arrays.asList("GET /simple_get HTTP/1.1");
        ResponseBuilder responseLine = parser.parse(requestLineList);
        String header = responseLine.setHeader();
        String body = responseLine.setBody();
        Response response = new Response(header, body);
        String actual = response.getResponseLine();
        String expected = String.join("\r\n", new String[]{
                "HTTP/1.1 200 OK",
                "Content-Length: 0",
                "",
                "",
                ""
        });
        assertEquals(expected, actual);
    }
}