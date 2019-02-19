package com.heatheryou.httpserver;

import com.heatheryou.httpserver.route.Router;
import com.heatheryou.httpserver.route.handler.BuildResponse;
import com.heatheryou.httpserver.route.handler.ResponseBuilder;

import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println(Arrays.toString(args));
            System.err.println("Usage: java HTTPServer <port number>");
            System.exit(1);
        }

        while (true) {
            BuildResponse buildResponse = new ResponseBuilder();
            Router router = new Router(buildResponse);
            RequestParser parser = new RequestParser();
            int port = Integer.parseInt(args[0]);

            try (
                    ServerSocketWrapper serverSocketWrapper = new ServerSocketWrapper(port);
                    Server server = new Server(serverSocketWrapper, router, parser)
            ) {
                server.start();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
