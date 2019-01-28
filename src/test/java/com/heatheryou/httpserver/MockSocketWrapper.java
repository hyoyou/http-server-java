package com.heatheryou.httpserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class MockSocketWrapper implements ISocketWrapper {
    private PrintWriter printWriter;
    private BufferedReader bufferedReader;

    public MockSocketWrapper(PrintWriter printWriter, PrintReader reader) {
        this.printWriter = printWriter;
        this.bufferedReader = new BufferedReader(reader);
    }

    @Override
    public PrintWriter getPrintWriter() {
        return printWriter;
    }

    @Override
    public BufferedReader getInputStreamReader() throws IOException {
        return bufferedReader;
    }
}
