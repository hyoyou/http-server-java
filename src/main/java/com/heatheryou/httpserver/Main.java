package com.heatheryou.httpserver;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        while (true) {
            Router router = new Router();
            RequestParser parser = new RequestParser();
            ResponseBuilder builder = new ResponseBuilder();

            try (
                    ServerSocketWrapper serverSocketWrapper = new ServerSocketWrapper(5000);
                    Server server = new Server(serverSocketWrapper, router, parser, builder)
            ) {
                server.start();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
