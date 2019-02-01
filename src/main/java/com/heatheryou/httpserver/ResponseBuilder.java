package com.heatheryou.httpserver;

public class ResponseBuilder {
    String method;
    String uri;
    String protocol;

    public ResponseBuilder(String method, String uri, String protocol) {
        this.method = method;
        this.uri = uri;
        this.protocol = protocol;
    }

    public String setHeader() {
        if (method.equals("GET") || method.equals("HEAD")) {
            return(
                "HTTP/1.1 200 OK\r\n" +
                "Content-Length: 0\r\n"
            );
        } else {
            return "Invalid Method";
        }
    }

    public String setBody() {
        if (uri.equals("/simple_get")) {
            return "\r\n";
        } else {
            return "";
        }
    }
}
