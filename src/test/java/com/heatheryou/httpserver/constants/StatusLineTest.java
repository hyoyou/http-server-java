package com.heatheryou.httpserver.constants;

import org.junit.Test;

import static com.heatheryou.httpserver.constants.StatusLine.STATUS_CODE_200;
import static org.junit.Assert.assertEquals;

public class StatusLineTest {
    @Test
    public void getReasonPhraseReturnsCorrectReasonPhraseProvidedAStatusCode() {
        StatusLine statusLine = new StatusLine();

        String actual = statusLine.getReasonPhrase(STATUS_CODE_200);
        String expected = "OK";

        assertEquals(expected, actual);
    }
}