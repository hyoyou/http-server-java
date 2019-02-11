package com.heatheryou.httpserver;

import com.heatheryou.httpserver.constants.CharacterSet;
import com.heatheryou.httpserver.constants.EntityHeader;
import org.junit.Before;
import org.junit.Test;

import static com.heatheryou.httpserver.constants.StatusLine.STATUS_CODE_200;
import static org.junit.Assert.assertEquals;

public class ResponseBuilderTest {
    ResponseBuilder builder;

    @Before
    public void setUp() {
        builder = new ResponseBuilder();
    }

    @Test
    public void setStatusLineSetsTheStatusLineGivenStatusCode() {
        builder.setStatusLine(STATUS_CODE_200);
        String actual = builder.getStatusLine();
        String expected = "HTTP/1.1 200 OK" + CharacterSet.CRLF;
        assertEquals(expected, actual);
    }

    @Test
    public void setEntityHeaderSetsTheEntityHeadersWhenEntityHeadersArePassedInAsStringArray() {
        String contentLength = EntityHeader.CONTENT_LENGTH + CharacterSet.SPACE + EntityHeader.CONTENT_LENGTH_0 + CharacterSet.CRLF;
        String[] entityArray = new String[]{contentLength};
        builder.setEntityHeader(entityArray);
        String actual = builder.getEntityHeader();
        String expected = "Content-Length: 0" + CharacterSet.CRLF;
        assertEquals(expected, actual);
    }

    @Test
    public void getHeaderReturnsFullHeaderWhenStatusLineAndEntityHeadersAreSet() {
        builder.setStatusLine(STATUS_CODE_200);
        String contentLength = EntityHeader.CONTENT_LENGTH + CharacterSet.SPACE + EntityHeader.CONTENT_LENGTH_0 + CharacterSet.CRLF;
        String[] entityArray = new String[]{contentLength};
        builder.setEntityHeader(entityArray);
        builder.setHeader();
        String actual = builder.getHeader();
        String expected = String.join("\r\n", new String[]{
                "HTTP/1.1 200 OK",
                "Content-Length: 0",
                ""
        });
        assertEquals(expected, actual);
    }

    @Test
    public void getBodyReturnsEmptyBodyWhenThereIsNoBody() {
        builder.setBody(CharacterSet.EMPTY);
        String actual = builder.getBody();
        String expected = CharacterSet.CRLF;
        assertEquals(expected, actual);
    }
}