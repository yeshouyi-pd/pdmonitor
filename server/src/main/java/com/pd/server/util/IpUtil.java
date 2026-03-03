package com.pd.server.util;

import javax.servlet.http.HttpServletRequest;

public class IpUtil {

    private IpUtil() {
    }

    public static String getClientIp(HttpServletRequest request) {
        String ip = firstIp(request.getHeader("X-Forwarded-For"));
        if (isEmptyOrUnknown(ip)) {
            ip = firstIp(request.getHeader("X-Real-IP"));
        }
        if (isEmptyOrUnknown(ip)) {
            ip = firstIp(request.getHeader("Proxy-Client-IP"));
        }
        if (isEmptyOrUnknown(ip)) {
            ip = firstIp(request.getHeader("WL-Proxy-Client-IP"));
        }
        if (isEmptyOrUnknown(ip)) {
            ip = request.getRemoteAddr();
        }
        if ("0:0:0:0:0:0:0:1".equals(ip)) {
            ip = "127.0.0.1";
        }
        return ip;
    }

    private static boolean isEmptyOrUnknown(String value) {
        return value == null || value.trim().isEmpty() || "unknown".equalsIgnoreCase(value.trim());
    }

    private static String firstIp(String headerValue) {
        if (headerValue == null) {
            return null;
        }
        String v = headerValue.trim();
        if (v.isEmpty()) {
            return null;
        }
        // X-Forwarded-For: client, proxy1, proxy2
        if (v.contains(",")) {
            v = v.split(",")[0].trim();
        }
        // Forwarded/Real-IP could be like: [2001:db8::1]:1234
        if (v.startsWith("[")) {
            int end = v.indexOf(']');
            if (end > 0) {
                return v.substring(1, end);
            }
        }
        // Strip port if present: 1.2.3.4:5678
        int colon = v.indexOf(':');
        if (colon > 0 && v.indexOf('.') > -1) {
            return v.substring(0, colon);
        }
        return v;
    }
}

