package com.heatheryou.httpserver;

public class CommandLineArgs {
    public static void validate(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java -jar http-server.jar <port number>");
            System.exit(1);
        }
    }

    public static int parsePort(String[] args) {
        return Integer.parseInt(args[0]);
    }
}