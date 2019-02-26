package com.heatheryou.httpserver;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.StringReader;

import static org.junit.Assert.*;

public class RequestValidatorTest {
    @Test
    public void requestValidatorDoesNotKillServerWithBadRequestAndPrintsException() {
        String requestLine = "BADREQUEST";
        StringReader stringReader = new StringReader(requestLine);
        BufferedReader bufferedReader = new BufferedReader(stringReader);
        MockSystemOutput systemOutput = new MockSystemOutput();
        RequestValidator validator = new RequestValidator(systemOutput);
        validator.validateRequest(bufferedReader);

        String actual = systemOutput.lastOutput;
        String expected = "Exception: Bad Request";
        assertEquals(expected, actual);
    }
}