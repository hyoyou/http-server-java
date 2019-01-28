package com.heatheryou.httpserver;

public class MockBufferedReader implements IBufferedReaderWrapper {
    private IBufferedReaderWrapper bufferedReaderWrapper;

    public MockBufferedReader(IBufferedReaderWrapper bufferedReaderWrapper) {
        this.bufferedReaderWrapper = bufferedReaderWrapper;
    }

    @Override
    public String readLine() {
        return null;
    }
}