package com.heatheryou.httpserver;

public class Request {
    private String method;
    private String uri;

    public Request(String uri, String method) {
        this.uri = uri;
        this.method = method;
    }

    public String getMethod() {
        return method;
    }

    public String getUri() {
        return uri;
    }
}
