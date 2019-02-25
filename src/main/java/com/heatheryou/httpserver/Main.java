package com.heatheryou.httpserver;

import com.heatheryou.httpserver.route.Router;
import com.heatheryou.httpserver.route.handler.BuildResponse;
import com.heatheryou.httpserver.route.handler.ResponseBuilder;

public class Main {
    public static void main(String[] args) throws Exception {
        SystemOutput systemOutput = new SystemOutput();

        if (CommandLineArgs.isInvalid(args)) {
            CommandLineArgs.displayErr(args, systemOutput);
        }

        while (true) {
            BuildResponse buildResponse = new ResponseBuilder();
            Router router = new Router(buildResponse);
            RequestParser parser = new RequestParser();
            int port = CommandLineArgs.parsePort(args);

            ServerSocketWrapper serverSocketWrapper = new ServerSocketWrapper(port);
            Server server = new Server(serverSocketWrapper, router, parser);

            server.start();
            serverSocketWrapper.close();
            server.close();
        }
    }
}