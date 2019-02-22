package com.heatheryou.httpserver.constants;

public class EntityHeader {
    public static final String ALLOWED_METHODS = "Allow:";
    public static final String CONTENT_LENGTH = "Content-Length:";
    public static final int CONTENT_LENGTH_0 = 0;
    public static final String LOCATION = "Location:";
    // This hard coding for http://0.0.0.0 looks suspicious...
    // But I see the http_server_spec asks for this specific location header.
    public static final String SIMPLE_GET_URL = "http://0.0.0.0:5000/simple_get";
}
