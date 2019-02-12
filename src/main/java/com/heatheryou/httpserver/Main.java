package com.heatheryou.httpserver;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println(args);
        while (true) {
            Router router = new Router();
            RequestParser parser = new RequestParser();
            ResponseBuilder builder = new ResponseBuilder();
            int port = 5000;

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
