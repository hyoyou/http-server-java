package com.heatheryou.httpserver.route.handler;

import com.heatheryou.httpserver.Request;
import com.heatheryou.httpserver.Response;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class OptionsHandlerTest {
    @Test
    public void optionsHandlerReturnsResponseThatIncludesAllowedMethods() {
        BuildResponse buildResponse = new ResponseBuilder();
        List<String> allowedMethods = new ArrayList<>(Arrays.asList("OPTIONS","HEAD","GET"));
        OptionsHandler handler = new OptionsHandler(buildResponse, allowedMethods);
        Request request = new Request("/method_options", "OPTIONS", null);
        Response response = handler.handle(request);
        String actual = response.getResponse();
        String expected = String.join("\r\n", new String[]{
                "HTTP/1.1 200 OK",
                "Allow: OPTIONS,HEAD,GET",
                "Content-Length: 0",
                "",
                ""
        });
        assertEquals(expected, actual);
    }
}