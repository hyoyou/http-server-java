package com.heatheryou.httpserver.constants;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class StatusLineTest {
    @Test
    public void ItReturnsAStatusLineProvidedAStatusCode() {
        StatusLine statusLine = new StatusLine();

        String actual = statusLine.getReasonPhrase(200);
        String expected = "OK";

        assertEquals(expected, actual);
    }
}