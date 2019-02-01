package com.heatheryou.httpserver;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RequestParserTest {
    @Test
    public void ParserReturnsResponseLineGivenRequestLine() {
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