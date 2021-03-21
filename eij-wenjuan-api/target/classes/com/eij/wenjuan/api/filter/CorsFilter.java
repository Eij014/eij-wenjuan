package com.eij.wenjuan.api.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.google.common.net.HttpHeaders;

/**
 * @author Eij<eij00014@gmail.com>
 * Created on 2021-03-06
 */

@Component
public class CorsFilter implements Filter {
    private static final List<String> ALLOW_ORIGINS = Arrays.asList("http://localhost:8081");
    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String header = httpServletRequest.getHeader(HttpHeaders.ORIGIN);
        if (header != null && ALLOW_ORIGINS.contains(header)) {
            httpServletResponse.setHeader("Access-Control-Allow-Origin", "http://localhost:8081");
            httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
            httpServletResponse.setHeader("Access-Control-Max-Age", "6000");
            httpServletResponse.setHeader("Access-Control-Allow-Headers",
                    "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
            httpServletResponse.setHeader("Content-Type", "application/json;charset-UTF-8");
            httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
