package com.heatheryou.httpserver.route.handler;

import com.heatheryou.httpserver.Response;
import com.heatheryou.httpserver.constants.CharacterSet;
import org.junit.Before;
import org.junit.Test;

import static com.heatheryou.httpserver.constants.StatusLine.STATUS_CODE_200;
import static org.junit.Assert.assertEquals;

public class ResponseBuilderTest {
    String[] entityHeaders;
    ResponseBuilder builder;
    Response response;

    @Before
    public void setUp() {
        entityHeaders = new String[]{"Content-Length: 0"};
        builder = new ResponseBuilder();
        response = builder.buildResponse(STATUS_CODE_200, entityHeaders, CharacterSet.EMPTY);
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
        // I don't remember my http RFC that well.  Is this the header?
        // I usually think of the "Headers" as everything after the status line and before the body.
        String actual = builder.getHeader();
        String expected = String.join("\r\n", new String[]{
                "HTTP/1.1 200 OK",
                "Content-Length: 0"
        });
        assertEquals(expected, actual);
    }
}