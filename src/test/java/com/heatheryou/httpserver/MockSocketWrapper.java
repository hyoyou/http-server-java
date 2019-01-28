package com.heatheryou.httpserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

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
    public BufferedReader getInputStreamReader() throws IOException {
        return null;
    }
}
