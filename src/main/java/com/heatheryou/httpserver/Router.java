package com.heatheryou.httpserver;

import com.heatheryou.httpserver.handler.*;

import java.util.*;

public class Router {

    public static Map<String, ArrayList<String>> routeMap;
    static {
        routeMap = new HashMap<>();
        routeMap.put("/redirect", new ArrayList<>(Arrays.asList("GET")));
        routeMap.put("/method_options", new ArrayList<>(Arrays.asList("OPTIONS","HEAD","GET")));
        routeMap.put("/method_options2", new ArrayList<>(Arrays.asList("POST","PUT","OPTIONS","HEAD","GET")));
        routeMap.put("/get_with_body", new ArrayList<>(Arrays.asList("OPTIONS","HEAD")));
        routeMap.put("/simple_get", new ArrayList<>(Arrays.asList("GET","HEAD")));
        routeMap.put("/echo_body", new ArrayList<>(Arrays.asList("POST")));
    }

    public static Map<String, IHandler> handlerMap;
    static {
        handlerMap = new HashMap<>();
        handlerMap.put("OPTIONS", new OptionsHandler());
        handlerMap.put("GET", new GetHandler());
        handlerMap.put("HEAD", new HeadHandler());
    }

    public boolean isValidRequest(String uri, String method) {
        List<String> methodList = allowedMethods(uri);
        return methodList.contains(method);
    }

    public List<String> allowedMethods(String uri) {
        return routeMap.get(uri);
    }

    private boolean isValidRoute(String uri) {
        return routeMap.containsKey(uri);
    }

    public IHandler handleRequest(Request request) {
        String uri = request.getUri();
        String method = request.getMethod();
        if (isValidRequest(uri, method)) {
            return getHandler(method);
        } else if (isValidRoute(uri)){
            return new MethodNotAllowedHandler();
        }
        return new NoRouteFoundHandler();
    }

    private IHandler getHandler(String method) {
        return handlerMap.get(method);
    }
}