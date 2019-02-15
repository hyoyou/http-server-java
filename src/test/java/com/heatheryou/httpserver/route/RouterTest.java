package com.heatheryou.httpserver.route;

import com.heatheryou.httpserver.route.handler.BuildResponse;
import com.heatheryou.httpserver.route.handler.ResponseBuilder;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class RouterTest {
    @Test
    public void allowedMethodsReturnsListOfAvailableMethodsGivenUri() {
        BuildResponse buildResponse = new ResponseBuilder();
        Router router = new Router(buildResponse);
        List<String> actual = router.allowedMethods("/simple_get");
        List<String> expected = Arrays.asList("GET", "HEAD");
        assertEquals(expected, actual);
    }
}