package com.heatheryou.httpserver;

public class MockSystemOutput implements ISystemOutput {
    String lastOutput;
    boolean exit;

    public MockSystemOutput() {
        this.lastOutput = "";
        this.exit = false;
    }

    @Override
    public void printErr(String args) {
        this.lastOutput = args;
    }

    @Override
    public void exit(int statusCode) {
        this.exit = true;
    }
}
