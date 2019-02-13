package com.heatheryou.httpserver;

import com.heatheryou.httpserver.route.RequestHandler;
import com.heatheryou.httpserver.route.handler.MethodNotAllowedHandler;
import com.heatheryou.httpserver.route.handler.NoRouteFoundHandler;
import com.heatheryou.httpserver.route.Router;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

public class RouterTest {
    @Test
    public void allowedMethodsReturnsListOfAvailableMethodsGivenUri() {
        Router router = new Router();
        List<String> actual = router.allowedMethods("/simple_get");
        List<String> expected = Arrays.asList("GET", "HEAD");
        assertEquals(expected, actual);
    }

    @Test
    public void isValidRequestReturnsTrueIfRequestedMethodAndUriAreSupported() {
        Router router = new Router();
        boolean actual = router.isValidRequest("/simple_get", "HEAD");
        assertTrue(actual);
    }

    @Test
    public void isValidRequestReturnsFalseIfRequestedUriIsValidButMethodNotSupported() {
        Router router = new Router();
        boolean actual = router.isValidRequest("/simple_get", "POST");
        assertFalse(actual);
    }

    @Test
    public void isValidRequestReturnsFalseIfRequestedUriAndMethodNotSupported() {
        Router router = new Router();
        boolean actual = router.isValidRequest("/invalid_uri", "POST");
        assertFalse(actual);
    }

    @Test
    public void isValidRouteReturnsTrueIfValidUriIsRequested() {
        Router router = new Router();
        boolean actual = router.isValidRoute("/simple_get");
        assertTrue(actual);
    }

    @Test
    public void isValidRouteReturnsFalseIfInvalidUriIsRequested() {
        Router router = new Router();
        boolean actual = router.isValidRoute("/invalid_uri");
        assertFalse(actual);
    }

    @Test
    public void handleRequestReturnsMethodNotAllowedHandlerIfValidUriWithInvalidMethodIsRequested() {
        Router router = new Router();
        Request request = new Request("/simple_get", "POST");
        RequestHandler actual = router.handleRequest(request);
        assertThat(actual, instanceOf(MethodNotAllowedHandler.class));
    }

    @Test
    public void handleRequestReturnsNoRouteFoundHandlerIfInvalidUriWithInvalidMethodIsRequested() {
        Router router = new Router();
        Request request = new Request("/invalid_route", "POST");
        RequestHandler actual = router.handleRequest(request);
        assertThat(actual, instanceOf(NoRouteFoundHandler.class));
    }
}