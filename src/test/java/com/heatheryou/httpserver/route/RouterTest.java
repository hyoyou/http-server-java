package com.heatheryou.httpserver.route;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class RouterTest {
    @Test
    public void allowedMethodsReturnsListOfAvailableMethodsGivenUri() {
        Router router = new Router();
        List<String> actual = router.allowedMethods("/simple_get");
        List<String> expected = Arrays.asList("GET", "HEAD");
        assertEquals(expected, actual);
    }
}