package com.heatheryou.httpserver;

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
}
