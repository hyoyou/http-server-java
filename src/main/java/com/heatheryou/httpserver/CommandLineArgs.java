package com.heatheryou.httpserver;

public class CommandLineArgs {
    public static void displayErr(ISystemOutput systemOutput) {
        systemOutput.printErr("Usage: java -jar build/libs/http-server-1.0.jar <port number>");
        systemOutput.exit(1);
    }

    public static boolean isInvalid(String[] args) {
        if (args.length != 1) { return true; }

        try {
            parsePort(args);
            return false;
        } catch(NumberFormatException e) {
            return true;
        }
    }

    public static int parsePort(String[] args) {
        return Integer.parseInt(args[0]);
    }
}