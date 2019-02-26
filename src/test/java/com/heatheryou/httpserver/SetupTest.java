package com.heatheryou.httpserver;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;

public class SetupTest {
    MockSystemOutput mockOutput;
    String[] args;
    Setup setup;

    @Before
    public void setUp() {
        mockOutput = new MockSystemOutput();
        args = new String[]{"random", "5000"};
        setup = new Setup(args, new CommandLineArgs(), mockOutput);
    }

    @Test
    public void executeReturnsPortNumberIfArgumentContainsOnlyIntegers() {
        String[] newArgs = new String[]{"3000"};
        setup = new Setup(newArgs, new CommandLineArgs(), mockOutput);

        int actual = setup.execute();
        int expected = 3000;
        assertEquals(actual, expected);
    }

    @Test
    public void executeExitsApplicationWithInvalidArguments() {
        setup.execute();

        boolean actual = mockOutput.exit;
        assertTrue(actual);
    }

    @Test
    public void executePrintsUsageToApplicationWithInvalidArguments() {
        setup.execute();

        String actual = mockOutput.lastOutput;
        String expected = "Usage: java -jar build/libs/http-server-1.0-SNAPSHOT.jar <port number>";
        assertEquals(actual, expected);
    }
}