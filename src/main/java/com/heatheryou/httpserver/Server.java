package com.heatheryou.httpserver;

import com.heatheryou.httpserver.route.RequestHandler;
import com.heatheryou.httpserver.route.Router;
import com.heatheryou.httpserver.route.handler.ResponseBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Server implements AutoCloseable {
    private IServerSocketWrapper serverSocketWrapper;
    private Router router;
    private RequestParser parser;

    public Server(IServerSocketWrapper serverSocketWrapper, Router router, RequestParser parser) {
        this.serverSocketWrapper = serverSocketWrapper;
        this.router = router;
        this.parser = parser;
    }

    public void start() throws IOException {
//         try {
             ISocketWrapper clientSocket = serverSocketWrapper.accept();
             BufferedReader requestReader = clientSocket.getInputStreamReader();
             PrintWriter printWriter = clientSocket.getPrintWriter();

             Request request = parser.processRequest(requestReader);
             RequestHandler handler = router.handleRequest(request);
             Response response = handler.handle(request);
             String responseString = response.getResponse();
             printWriter.print(responseString);
             printWriter.flush();
             // Do we want to let these exceptions bubble up to the top?
             // If we're printing them anyway, then I think we get that "for free" if we just let them bubble
             //    up and kill our program.
             // If we want to improve the visual appearance of them, that might be one reason we could do this instead.
  //       } catch (Exception e) {
             // This doesn't seem to print my exception message.
             // We ran into this before, I believe.  You should probably try to track something like that down.
             // https://stackoverflow.com/questions/41204142/java-exception-getmessage-returns-1
    //         System.out.println("Exception: " + e.toString());
      //   }
    }

    @Override
    public void close() throws Exception {
        // Is this supposed to call serverSocketWrapper.close() ?
    }
}