package com.heatheryou.httpserver;

import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("Usage: java HTTPServer <port number>");
            System.exit(1);
        }

        System.out.println(Arrays.toString(args));
        while (true) {
            Router router = new Router();
            RequestParser parser = new RequestParser();
            ResponseBuilder builder = new ResponseBuilder();
            int port = Integer.parseInt(args[0]);

            try (
                    ServerSocketWrapper serverSocketWrapper = new ServerSocketWrapper(port);
                    Server server = new Server(serverSocketWrapper, router, parser, builder)
            ) {
                server.start();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
