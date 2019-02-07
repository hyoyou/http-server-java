package com.heatheryou.httpserver;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class RouterTest {
    @Test
    public void ItReturnsListOfAvailableMethodsGivenUri() {
        Router router = new Router();
        List<String> actual = router.allowedMethods("/simple_get");
        List<String> expected = Arrays.asList("GET", "HEAD");
        assertEquals(expected, actual);
    }

    @Test
    public void ItReturnsTrueIfRequestedMethodAndUriAreSupported() {
        Router router = new Router();
        boolean actual = router.isValidRequest("/simple_get", "HEAD");
        assertTrue(actual);
    }

    @Test
    public void ItReturnsFalseIfRequestedMethodAndUriNotSupported() {
        Router router = new Router();
        boolean actual = router.isValidRequest("/simple_get", "POST");
        assertFalse(actual);
    }

}