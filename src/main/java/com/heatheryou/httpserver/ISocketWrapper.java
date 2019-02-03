package com.heatheryou.httpserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public interface ISocketWrapper {
    PrintWriter getPrintWriter() throws IOException;
    BufferedReader getInputStreamReader() throws IOException;
}
