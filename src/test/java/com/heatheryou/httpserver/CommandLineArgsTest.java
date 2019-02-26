package com.heatheryou.httpserver;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class CommandLineArgsTest {
    @Test
    public void isInvalidDeterminesValidityOfRequestAndReturnsTrueForInvalidRequestWithStringAndInteger() {
        String[] args = new String[]{"random", "5000"};

        boolean actual = CommandLineArgs.isInvalid(args);
        assertTrue(actual);
    }

    @Test
    public void isInvalidDeterminesValidityOfRequestAndReturnsTrueForInvalidRequestWithString() {
        String[] args = new String[]{"random"};

        boolean actual = CommandLineArgs.isInvalid(args);
        assertTrue(actual);
    }

    @Test
    public void isInvalidDeterminesValidityOfRequestAndReturnsFalseForValidRequest() {
        String[] args = new String[]{"5000"};

        boolean actual = CommandLineArgs.isInvalid(args);
        assertFalse(actual);
    }

    @Test
    public void validateExitsApplicationWithInvalidRequest() {
        MockSystemOutput mockOutput = new MockSystemOutput();
        String[] args = new String[]{"random", "5000"};
        CommandLineArgs.displayErr(mockOutput);

        boolean actual = mockOutput.exit;
        assertTrue(actual);
    }

    @Test
    public void validatePrintsUsageToApplicationWithInvalidRequest() {
        MockSystemOutput mockOutput = new MockSystemOutput();
        String[] args = new String[]{"random", "5000"};
        CommandLineArgs.displayErr(mockOutput);

        String actual = mockOutput.lastOutput;
        String expected = "Usage: java -jar build/libs/http-server-1.0.jar <port number>";
        assertEquals(actual, expected);
    }

    @Test
    public void parsePortExtractsPortNumberFromValidRequest() {
        String[] args = new String[]{"8080"};

        int actual = CommandLineArgs.parsePort(args);
        int expected = 8080;
        assertEquals(actual, expected);
    }

    @Test(expected = NumberFormatException.class)
    public void parsePortThrowsErrorOnInvalidRequest() {
        String[] args = new String[]{"abcde"};
        CommandLineArgs.parsePort(args);
    }
}