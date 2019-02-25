package com.heatheryou.httpserver;

public class SystemWrapper implements ISystemWrapper {
    private System system;

    public SystemWrapper(System system) { this.system = system; };

    @Override
    public void printOutput(String args) {
        system.out.println(args);
    }

    @Override
    public void printError(String args) {
        system.err.println(args);
    }

    @Override
    public void exit(int statusCode) {
        system.exit(statusCode);
    }
}
