package com.heatheryou.httpserver;

public class Request {
    private String method;
    private String uri;
    private String body;

    public Request(String uri, String method) {
        this.uri = uri;
        this.method = method;
    }

    public Request(String uri, String method, String body) {
        this.uri = uri;
        this.method = method;
        this.body = body;
    }

    public String getMethod() {
        return method;
    }

    public String getUri() {
        return uri;
    }

    public String getBody() {
        return body;
    }
}
