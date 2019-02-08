package com.heatheryou.httpserver;

import java.io.*;

public class MockSocketWrapper implements ISocketWrapper {
    private PrintWriter printWriter;
    private BufferedReader bufferedReader;

    public MockSocketWrapper(PrintWriter printWriter, BufferedReader bufferedReader) {
        this.printWriter = printWriter;
        this.bufferedReader = bufferedReader;
    }

    @Override
    public PrintWriter getPrintWriter() {
        return printWriter;
    }

    @Override
    public BufferedReader getInputStreamReader() { return bufferedReader; }
}