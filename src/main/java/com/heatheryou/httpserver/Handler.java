package com.heatheryou.httpserver;

import static com.heatheryou.httpserver.constants.StatusLine.STATUS_CODE_200;

public class Handler {

    public static void options(String uri) {
        switch (uri) {
            case "/method_options":
                break;
            case "/method_options2":
                break;
        }
    }

    public static void get(String uri) {
        switch (uri) {
            case "/simple_get":
                ResponseBuilder responseBuilder = new ResponseBuilder();
                responseBuilder.setHeader(STATUS_CODE_200);
                break;
            case "/redirect":
                break;
            case "/not_found_resource":
                break;
            default:
                break;
        }
    }

    public static void head(String uri) {
        switch (uri) {
            case "/simple_get":
                break;
            case "/get_with_body":
                break;
        }
    }

    public static void post(String uri) {
        switch (uri) {
            case "/echo_body":
                break;
        }
    }

    public static void put(String uri) {
        switch (uri) {
            case "/method_options2":
                break;
        }
    }
}
