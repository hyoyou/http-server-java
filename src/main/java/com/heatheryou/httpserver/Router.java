package com.heatheryou.httpserver;

import java.util.*;

public class Router {
    public String method;
    public String uri;

    public enum Method {
        OPTIONS, GET, HEAD, POST, PUT
    }

    public static Map<String, ArrayList<String>> routeMap;
    static {
        routeMap = new HashMap<>();
        routeMap.put("/redirect", new ArrayList<>(Arrays.asList("GET")));
        routeMap.put("/method_options", new ArrayList<>(Arrays.asList("OPTIONS", "HEAD", "GET")));
        routeMap.put("/method_options2", new ArrayList<>(Arrays.asList("POST", "PUT", "OPTIONS", "HEAD", "GET")));
        routeMap.put("/not_found_resource", new ArrayList<>(Arrays.asList("GET")));
        routeMap.put("/get_with_body", new ArrayList<>(Arrays.asList("OPTIONS", "HEAD")));
        routeMap.put("/simple_get", new ArrayList<>(Arrays.asList("GET", "HEAD")));
        routeMap.put("/echo_body", new ArrayList<>(Arrays.asList("POST")));
    }

    public static Map<String, Runnable> handlerMap;
    {
        handlerMap = new HashMap<>();
        handlerMap.put("OPTIONS", () -> Handler.options(uri));
        handlerMap.put("GET", () -> Handler.get(uri));
        handlerMap.put("HEAD", () -> Handler.head(uri));
        handlerMap.put("POST", () -> Handler.post(uri));
        handlerMap.put("PUT", () -> Handler.put(uri));
    }

    public boolean isValidRequest(String uri, String method) {
        List<String> methodList = allowedMethods(uri);
        return methodList.contains(method);
    }

    public List<String> allowedMethods(String uri) {
        return routeMap.get(uri);
    }

    public void setRequest(String uri, String method) {
        if (isValidRequest(uri, method)) {
            setUri(uri);
            setMethod(method);
        }
    }

    public void handleRequest(String method) {
        getHandler(method).run();
    }

    private void setUri(String uri) {
        this.uri = uri;
    }

    private void setMethod(String method) {
        this.method = method;
    }

    public String getUri() {
        return uri;
    }

    public String getMethod() {
        return method;
    }

    public Runnable getHandler(String method) {
        return handlerMap.get(method);
    }
}
