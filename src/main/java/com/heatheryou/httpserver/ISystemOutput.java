package com.heatheryou.httpserver;

public interface ISystemWrapper {
    void printOutput(String args);
    void printError(String args);
    void exit(int statusCode);
}
