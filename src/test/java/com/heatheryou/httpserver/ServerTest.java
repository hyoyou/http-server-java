package com.heatheryou.httpserver;

import com.heatheryou.httpserver.route.Router;
import com.heatheryou.httpserver.route.handler.BuildResponse;
import com.heatheryou.httpserver.route.handler.ResponseBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

public class ServerTest {
    private Router router;
    private RequestParser parser;
    private BuildResponse buildResponse;

    @Before
    public void setUp() {
        buildResponse = new ResponseBuilder();
        router = new Router(buildResponse);
        parser = new RequestParser();
    }

    @Test
    public void serverAnswersSimpleGet() throws IOException {
        String requestLine = "GET /simple_get HTTP/1.1";
        StringReader stringReader = new StringReader(requestLine);
        BufferedReader bufferedReader = new BufferedReader(stringReader);
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        MockSocketWrapper mockSocketWrapper = new MockSocketWrapper(printWriter, bufferedReader);
        MockServerSocketWrapper mockServerSocketWrapper = new MockServerSocketWrapper(mockSocketWrapper);
        Server server = new Server(mockServerSocketWrapper, router, parser);

        server.start();

        String actual = stringWriter.toString();
        String expected = String.join("\r\n", new String[]{
                "HTTP/1.1 200 OK",
                "Content-Length: 0",
                "",
                ""
        });

        assertEquals(expected, actual);
    }

    @Test
    public void serverAnswersSimpleOptions() throws IOException {
        String requestLine = "OPTIONS /method_options HTTP/1.1";
        StringReader stringReader = new StringReader(requestLine);
        BufferedReader bufferedReader = new BufferedReader(stringReader);
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        MockSocketWrapper mockSocketWrapper = new MockSocketWrapper(printWriter, bufferedReader);
        MockServerSocketWrapper mockServerSocketWrapper = new MockServerSocketWrapper(mockSocketWrapper);
        Server server = new Server(mockServerSocketWrapper, router, parser);

        server.start();

        String actual = stringWriter.toString();
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