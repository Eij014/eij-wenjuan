package com.eij.wenjuan.component.utils.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Eij<eij00014@gmail.com>
 * Created on 2021-02-06
 */
public class HttpRequestUtils {
    private static final Logger logger = LoggerFactory.getLogger(HttpRequestUtils.class);

    private static final String KEY = "testKey";
    public static final String TOKEN_IDENTIFY = "userToken";
    public static final String JSESSIONID = "JSESSIONID";

    public static String getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
