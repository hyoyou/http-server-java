package com.heatheryou.httpserver;

public interface ISystemOutput {
    void printErr(String args);
    void exit(int statusCode);
}
