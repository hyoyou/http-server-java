package com.heatheryou.httpserver.route;

import com.heatheryou.httpserver.Request;
import com.heatheryou.httpserver.route.handler.*;

import java.util.*;

public class Router {

    private static Map<String, ArrayList<String>> routeMap;
    static {
        routeMap = new HashMap<>();
        routeMap.put("/method_options", new ArrayList<>(Arrays.asList("OPTIONS","HEAD","GET")));
        routeMap.put("/method_options2", new ArrayList<>(Arrays.asList("POST","PUT","OPTIONS","HEAD","GET")));
        routeMap.put("/get_with_body", new ArrayList<>(Arrays.asList("OPTIONS","HEAD")));
        routeMap.put("/simple_get", new ArrayList<>(Arrays.asList("GET","HEAD")));
        routeMap.put("/redirect", new ArrayList<>(Arrays.asList("GET")));
    }

    private static Map<String, RequestHandler> handlerMap;
    static {
        handlerMap = new HashMap<>();
        handlerMap.put("OPTIONS", new OptionsHandler());
        handlerMap.put("GET", new GetHandler());
        handlerMap.put("HEAD", new GetHandler());
    }

    public RequestHandler handleRequest(Request request) {
        String uri = request.getUri();
        String method = request.getMethod();
        if (isValidRequest(uri, method)) {
            return getHandler(method);
        } else if (isValidRoute(uri)){
            return new MethodNotAllowedHandler();
        }
        return new NoRouteFoundHandler();
    }


    private boolean isValidRequest(String uri, String method) {
        if (allowedMethods(uri) != null) {
            List<String> methodList = allowedMethods(uri);
            return methodList.contains(method);
        }
        return false;
    }

    public List<String> allowedMethods(String uri) {
        return routeMap.get(uri);
    }

    private RequestHandler getHandler(String method) {
        return handlerMap.get(method);
    }

    private boolean isValidRoute(String uri) {
        return routeMap.containsKey(uri);
    }
}
