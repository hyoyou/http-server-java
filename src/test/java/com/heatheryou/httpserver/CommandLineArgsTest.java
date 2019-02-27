package com.heatheryou.httpserver;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class CommandLineArgsTest {
    CommandLineArgs commandLineArgs;

    @Before
    public void setUp() {
        commandLineArgs = new CommandLineArgs();
    }

    @Test
    public void isValidDeterminesValidityOfRequestAndReturnsFalseForInvalidRequestWithStringAndInteger() {
        String[] args = new String[]{"random", "5000"};

        boolean actual = commandLineArgs.isValid(args);
        assertFalse(actual);
    }

    @Test
    public void isValidDeterminesValidityOfRequestAndReturnsFalseForInvalidRequestWithString() {
        String[] args = new String[]{"random"};

        boolean actual = commandLineArgs.isValid(args);
        assertFalse(actual);
    }

    @Test
    public void isValidDeterminesValidityOfRequestAndReturnsTrueForValidRequest() {
        String[] args = new String[]{"5000"};

        boolean actual = commandLineArgs.isValid(args);
        assertTrue(actual);
    }

    @Test
    public void parsePortExtractsPortNumberFromValidRequest() {
        String[] args = new String[]{"8080"};

        int actual = commandLineArgs.parsePort(args);
        int expected = 8080;
        assertEquals(actual, expected);
    }

    @Test(expected = NumberFormatException.class)
    public void parsePortThrowsErrorOnInvalidRequest() {
        String[] args = new String[]{"abcde"};
        commandLineArgs.parsePort(args);
    }
}