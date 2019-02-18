package com.heatheryou.httpserver.route;

import com.heatheryou.httpserver.Request;
import com.heatheryou.httpserver.route.handler.*;

import java.util.*;

public class Router {
    private static BuildResponse buildResponse;

    private static Map<String, ArrayList<String>> routeMap;
    static {
        routeMap = new HashMap<>();
        routeMap.put("/method_options", new ArrayList<>(Arrays.asList("OPTIONS","HEAD","GET")));
        routeMap.put("/method_options2", new ArrayList<>(Arrays.asList("POST","PUT","OPTIONS","HEAD","GET")));
        routeMap.put("/get_with_body", new ArrayList<>(Arrays.asList("OPTIONS","HEAD")));
        routeMap.put("/simple_get", new ArrayList<>(Arrays.asList("GET","HEAD")));
        routeMap.put("/redirect", new ArrayList<>(Arrays.asList("GET")));
        routeMap.put("/echo_body", new ArrayList<>(Arrays.asList("POST")));
    }

    private Map<String, RequestHandler> handlerMap;

    public Router(BuildResponse buildResponse) {
        this.buildResponse = buildResponse;
        handlerMap = new HashMap<>();
        handlerMap.put("GET", new GetHandler(buildResponse));
        handlerMap.put("HEAD", new GetHandler(buildResponse));
        handlerMap.put("POST", new PostHandler(buildResponse));
    }

    public RequestHandler handleRequest(Request request) {
        String uri = request.getUri();
        String method = request.getMethod();
        List<String> allowedMethods = allowedMethods(uri);
        if (isValidRequest(uri, method)) {
            if (method.equals("OPTIONS")) {
                return new OptionsHandler(buildResponse, allowedMethods);
            }
            return getHandler(method);
        } else if (isValidRoute(uri)){
            return new MethodNotAllowedHandler(buildResponse, allowedMethods);
        }
        return new NoRouteFoundHandler(buildResponse);
    }

    private boolean isValidRequest(String uri, String method) {
        if (allowedMethods(uri) != null) {
            List<String> methodList = allowedMethods(uri);
            return methodList.contains(method);
        }
        return false;
    }

    private List<String> allowedMethods(String uri) { return routeMap.get(uri); }

    private RequestHandler getHandler(String method) { return handlerMap.get(method); }

    private boolean isValidRoute(String uri) {
        return routeMap.containsKey(uri);
    }
}