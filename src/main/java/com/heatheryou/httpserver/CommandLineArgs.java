package com.heatheryou.httpserver;

public class CommandLineArgs {
    public static boolean isInvalid(String[] args) {
        return args.length != 1;
    }

    public static int parsePort(String[] args) {
        return Integer.parseInt(args[0]);
    }
}