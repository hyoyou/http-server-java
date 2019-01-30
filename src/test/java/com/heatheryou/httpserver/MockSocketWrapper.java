package com.heatheryou.httpserver;

import java.io.*;

public class MockSocketWrapper implements ISocketWrapper {
    private PrintWriter printWriter;

    public MockSocketWrapper(PrintWriter printWriter) {
        this.printWriter = printWriter;
    }

    @Override
    public PrintWriter getPrintWriter() {
        return printWriter;
    }

    @Override
    public BufferedReader getInputStreamReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }
}