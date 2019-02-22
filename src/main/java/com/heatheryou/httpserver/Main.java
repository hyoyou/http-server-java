package com.heatheryou.httpserver;

import com.heatheryou.httpserver.route.Router;
import com.heatheryou.httpserver.route.handler.BuildResponse;
import com.heatheryou.httpserver.route.handler.ResponseBuilder;

import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        // Extract to class and test?
        // What if the args are strings?
        if (args.length != 1) {
            // Why are we printing this...?
            System.out.println(Arrays.toString(args));
            // This isn't really the correct instructions for the command line invocation, is it?
            //    Should be something like "java -jar httpserver <port number>"...?
            System.err.println("Usage: java HTTPServer <port number>");
            // Good.  Non-zero status code.
            System.exit(1);
        }

        while (true) {
            BuildResponse buildResponse = new ResponseBuilder();
            Router router = new Router(buildResponse);
            RequestParser parser = new RequestParser();
            int port = Integer.parseInt(args[0]);

            //try (
                    ServerSocketWrapper serverSocketWrapper = new ServerSocketWrapper(port);
                    Server server = new Server(serverSocketWrapper, router, parser);
            //) {
                server.start();
                serverSocketWrapper.close();
                server.close();
            //} catch (Exception e) {
              //  System.out.println("I AM HERE!");
                // This failed us once before, didn't it?  Is e.getMessage() correct?
                //System.out.println(e.getMessage());
            //}
        }
    }
}
