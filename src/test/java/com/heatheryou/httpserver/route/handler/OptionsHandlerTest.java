package com.heatheryou.httpserver.route.handler;

import com.heatheryou.httpserver.Request;
import com.heatheryou.httpserver.Response;
import com.heatheryou.httpserver.constants.CharacterSet;
import com.heatheryou.httpserver.constants.EntityHeader;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OptionsHandlerTest {
    @Test
    public void optionsHandlerReturnsResponseThatIncludesAllowedMethods() {
        BuildResponse buildResponse = new ResponseBuilder();
        OptionsHandler handler = new OptionsHandler(buildResponse);
        Request request = new Request("/method_options", "OPTIONS");
        Response response = handler.handle(request);
        String actual = response.getResponseLine();
        String expected = String.join("\r\n", new String[]{
                "HTTP/1.1 200 OK",
                "Allow: OPTIONS,HEAD,GET",
                "Content-Length: 0",
                "",
                "",
                ""
        });
        assertEquals(expected, actual);
    }
}