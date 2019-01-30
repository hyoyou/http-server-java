package com.heatheryou.httpserver;

import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.*;

public class ServerTest {
    @Test
    public void ServerAnswersSimpleGet() throws IOException {
        String requestLine = "GET /simple_get HTTP/1.1\n";
        InputStream stream = new ByteArrayInputStream((requestLine+"\n").getBytes(StandardCharsets.UTF_8));
        InputStream stdin = System.in;
        System.setIn(stream);
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        MockSocketWrapper mockSocketWrapper = new MockSocketWrapper(printWriter);
        MockServerSocketWrapper mockServerSocketWrapper = new MockServerSocketWrapper(mockSocketWrapper);
        Server server = new Server(mockServerSocketWrapper);

        server.start();

        String actual = stringWriter.toString();
        String expected = String.join("\n", new String[]{
                "HTTP/1.1 200 OK",
                "Content-Length: 0",
                "",
                ""
        });
        assertEquals(expected, actual);
        System.setIn(stdin);
    }
}