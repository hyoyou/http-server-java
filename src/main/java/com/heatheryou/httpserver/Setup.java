package com.heatheryou.httpserver;

public class Setup {
    String[] args;
    CommandLineArgs commandLineArgs;
    ISystemOutput systemOutput;

    public Setup(String[] args, CommandLineArgs commandLineArgs, ISystemOutput systemOutput) {
        this.args = args;
        this.commandLineArgs = commandLineArgs;
        this.systemOutput = systemOutput;
    }

    public int execute() {
        if (commandLineArgs.isValid(args)) {
            return commandLineArgs.parsePort(args);
        }
        executeError();
        return 0;
    }

    private void executeError() {
        systemOutput.printErr("Usage: java -jar build/libs/http-server-1.0-SNAPSHOT.jar <port number>");
        systemOutput.exit(1);
    }
}