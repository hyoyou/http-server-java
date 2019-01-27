package com.heatheryou.httpserver;

import java.io.IOException;
import java.io.PrintWriter;

public interface ISocketWrapper {
    PrintWriter getPrintWriter() throws IOException;
}
