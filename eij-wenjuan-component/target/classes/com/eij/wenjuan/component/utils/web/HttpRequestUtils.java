package com.eij.wenjuan.component.utils.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * @author Eij<eij00014@gmail.com>
 * Created on 2021-02-06
 */
public class HttpRequestUtils {
    private static final Logger logger = LoggerFactory.getLogger(HttpRequestUtils.class);

    private static final String KEY = "5anbzl6329lzb";
    public static final String TOKEN_IDENTIFY = "userToken";
    public static final String JSESSIONID = "JSESSIONID";

    public static String getAccountFromToken(String token) {
        String[] split = StringUtils.split(token, "-");
        if (split.length == 2) {
            byte[] bytes = Base64.decodeBase64(split[0]);
            return split[1];
        }
        return null;
    }

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
