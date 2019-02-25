package com.heatheryou.httpserver;

public class SystemOutput implements ISystemOutput {
    @Override
    public void printErr(String message) {
        System.err.println(message);
    }

    @Override
    public void exit(int statusCode) {
        System.exit(statusCode);
    }
}