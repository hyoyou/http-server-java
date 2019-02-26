package com.heatheryou.httpserver;

public class CommandLineArgs {
    public static boolean isValid(String[] args) {
        if (args.length != 1) { return false; }

        try {
            parsePort(args);
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
    }

    public static int parsePort(String[] args) {
        return Integer.parseInt(args[0]);
    }
}