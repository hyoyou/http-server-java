package com.heatheryou.httpserver;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class CommandLineArgsTest {
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalErr = System.err;

    @Before
    public void setUp() {
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restore() {
        System.setErr(originalErr);
    }

    @Ignore
    public void commandLineArgsPrintsErrorWhenInvalidRequestReceived() {
        String[] args = new String[]{"random", "5000"};
        CommandLineArgs.validate(args);

        String actual = errContent.toString();
        String expected = "Usage: java -jar http-server.jar <port number>";
        assertEquals(actual, expected);
    }

    @Test
    public void commandLineArgsExtractsPortNumberFromValidRequest() {
        String[] args = new String[]{"8080"};

        int actual = CommandLineArgs.parsePort(args);
        int expected = 8080;
        assertEquals(actual, expected);
    }
}
