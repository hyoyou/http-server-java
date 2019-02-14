package com.heatheryou.httpserver;

import com.heatheryou.httpserver.constants.CharacterSet;
import com.heatheryou.httpserver.route.handler.ResponseBuilder;
import org.junit.Before;
import org.junit.Test;

import static com.heatheryou.httpserver.constants.StatusLine.STATUS_CODE_200;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

public class ResponseBuilderBuilderTest {
    String[] entityHeaders;
    ResponseBuilder builder;
    com.heatheryou.httpserver.Response response;

    @Before
    public void setUp() {
        entityHeaders = new String[]{"Content-Length: 0"};
        builder = new ResponseBuilder();
        response = builder.buildResponse(STATUS_CODE_200, entityHeaders, CharacterSet.EMPTY);
    }

    @Test
    public void getResponseLineReturnsResponseLineProvidedHeaderAndBody() {
        Response response = new Response("HTTP/1.1 200 OK\r\nContent-Length: 0\r\n", "\r\n");
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

    @Test
    public void buildResponseReturnsAResponseObject() {
        assertThat(response, instanceOf(com.heatheryou.httpserver.Response.class));
    }

    @Test
    public void getStatusLineReturnsStatusLine() {
        String actual = builder.getStatusLine();
        String expected = "HTTP/1.1 200 OK\r\n";
        assertEquals(expected, actual);
    }

    @Test
    public void getEntityHeaderReturnsEntityHeader() {
        String actual = builder.getEntityHeader();
        String expected = "Content-Length: 0";
        assertEquals(expected, actual);
    }

    @Test
    public void getHeaderReturnsStatusLineAndEntityHeader() {
        String actual = builder.getHeader();
        String expected = String.join("\r\n", new String[]{
                "HTTP/1.1 200 OK",
                "Content-Length: 0"
        });
        assertEquals(expected, actual);
    }
}