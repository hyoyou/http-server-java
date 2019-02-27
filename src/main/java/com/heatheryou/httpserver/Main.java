package com.heatheryou.httpserver;

import com.heatheryou.httpserver.route.Router;
import com.heatheryou.httpserver.route.handler.BuildResponse;
import com.heatheryou.httpserver.route.handler.ResponseBuilder;

public class Main {
    public static void main(String[] args) throws Exception {
        ISystemOutput systemOutput = new SystemOutput();
        int port = 5000;

        while (true) {
            BuildResponse buildResponse = new ResponseBuilder();
            Router router = new Router(buildResponse);
            RequestParser parser = new RequestParser(systemOutput);

            ServerSocketWrapper serverSocketWrapper = new ServerSocketWrapper(port);
            Server server = new Server(serverSocketWrapper, router, parser);

            server.start();

            server.close();
        }
    }
}