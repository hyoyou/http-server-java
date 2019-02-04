package com.heatheryou.httpserver.constants;

import java.util.HashMap;
import java.util.Map;

public class StatusLine {
    public static final String HTTP_VERSION = "HTTP/1.1";

    public static final int STATUS_CODE_200 = 200;
    public static final int STATUS_CODE_301 = 301;
    public static final int STATUS_CODE_404 = 404;
    public static final int STATUS_CODE_405 = 405;

    public static final String REASON_PHRASE_200 = "OK";
    public static final String REASON_PHRASE_301 = "Moved Permanently";
    public static final String REASON_PHRASE_404 = "Not Found";
    public static final String REASON_PHRASE_405 = "Method Not Allowed";

    public static final String SP = " ";
    public static final String CRLF = "\r\n";

    public static Map<Integer, String> statusCodeMessage;
    static {
        statusCodeMessage = new HashMap<Integer, String>();
        statusCodeMessage.put(STATUS_CODE_200, REASON_PHRASE_200);
        statusCodeMessage.put(STATUS_CODE_301, REASON_PHRASE_301);
        statusCodeMessage.put(STATUS_CODE_404, REASON_PHRASE_404);
        statusCodeMessage.put(STATUS_CODE_405, REASON_PHRASE_405);
    }

    public static String getReasonPhrase(int statusCode) {
        return statusCodeMessage.get(statusCode);
    }
}
