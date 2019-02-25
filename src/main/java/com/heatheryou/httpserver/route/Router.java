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

    public Router(BuildResponse buildResponse) {
        this.buildResponse = buildResponse;
    }

    public RequestHandler handleRequest(Request request) {
        String uri = request.getUri();
        String method = request.getMethod();
        List<String> allowedMethods = getAllowedMethods(uri);

        if (isGetOrHead(uri, method)) {
            return new GetHandler(buildResponse);
        }

        if (isOptions(uri, method)) {
            return new OptionsHandler(buildResponse, allowedMethods);
        }

        if (isPost(uri, method)) {
            return new PostHandler(buildResponse);
        }

        if (isValidRoute(uri)){
            return new MethodNotAllowedHandler(buildResponse, allowedMethods);
        }

        return new NoRouteFoundHandler(buildResponse);
    }

    private boolean isGetOrHead(String uri, String method) {
        return isValidRequest(uri, method) && (method.equals("GET") || method.equals("HEAD"));
    }

    private boolean isOptions(String uri, String method) {
        return isValidRequest(uri, method) && method.equals("OPTIONS");
    }

    private boolean isPost(String uri, String method) {
        return isValidRequest(uri, method) && method.equals("POST");
    }

    private boolean isValidRequest(String uri, String method) {
        if (getAllowedMethods(uri) != null) {
            List<String> methodList = getAllowedMethods(uri);
            return methodList.contains(method);
        }
        return false;
    }

    private List<String> getAllowedMethods(String uri) { return routeMap.get(uri); }

    private boolean isValidRoute(String uri) {
        return routeMap.containsKey(uri);
    }
}