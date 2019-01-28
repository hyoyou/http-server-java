package com.heatheryou.httpserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Server implements IBufferedReaderWrapper, AutoCloseable {
    final private IServerSocketWrapper serverSocketWrapper;

    public Server(IServerSocketWrapper serverSocketWrapper) {
        this.serverSocketWrapper = serverSocketWrapper;
    }

    public void start() throws IOException {
        try {
            while (true) {
                ISocketWrapper socketWrapper = serverSocketWrapper.accept();
                BufferedReader requestReader = socketWrapper.getInputStreamReader();
                String inputLine;
                while ((inputLine = requestReader.readLine()) != null) {
                    String[] requestParam = inputLine.split(" ");

                    String method = requestParam[0];
                    String uri = requestParam[1];

                    if (method == "GET") {
                        PrintWriter printWriter = socketWrapper.getPrintWriter();
                        printWriter.println("HTTP/1.1 200 OK");
                        printWriter.println("Content-Length: 0");
                        printWriter.println("");
                    }
                }

            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void close() throws Exception {

    }

    @Override
    public String readLine() {
        return null;
    }
}
