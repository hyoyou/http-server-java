package com.heatheryou.httpserver;

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class ServerTest {
    @Test
    public void ServerAnswersSimpleGet() throws IOException {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        StringReader stringReader = new StringReader("GET /simple_get");
        MockBufferedReader requestReader = new MockBufferedReader();
        MockSocketWrapper mockSocketWrapper = new MockSocketWrapper(printWriter, requestReader);
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

    }

    @Test
    public void ServerAnswersSimpleHead() throws IOException {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        StringReader stringReader = new StringReader(" ")
        BufferedReader requestReader = new MockBufferedReader(stringReader);
        MockSocketWrapper mockSocketWrapper = new MockSocketWrapper(printWriter, requestReader);
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
    }
}