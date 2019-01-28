package com.heatheryou.httpserver;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        try (
                ServerSocketWrapper serverSocketWrapper = new ServerSocketWrapper(5000);
                Server server = new Server(serverSocketWrapper)
        ) {
            server.start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
