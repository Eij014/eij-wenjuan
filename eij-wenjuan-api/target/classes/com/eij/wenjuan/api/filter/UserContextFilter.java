package com.eij.wenjuan.api.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.eij.wenjuan.component.bean.UserContext;
import com.eij.wenjuan.component.utils.web.HttpRequestUtils;
import com.eij.wenjuan.component.utils.web.LoginUserContext;

/**
 * @author Zhu Haojie<eij00014@gmail.com>
 * Created on 2021-02-05
 */
public class UserContextFilter implements Filter {

    private static final String ROOT_PATH = "/";

    private static final String VERSION_PATH = "/v";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String path = ((HttpServletRequest) request).getServletPath();
//        if (ROOT_PATH.equals(path)) {
//        }
    }

    private void setUserContext(ServletRequest request) {
        String userName = String.valueOf(request.getAttribute("userName"));
        String sessionId = HttpRequestUtils.getCookie((HttpServletRequest) request, HttpRequestUtils.JSESSIONID);

        UserContext userContext = new UserContext();
        userContext.setUserName(userName);
        userContext.setjSessionId(sessionId);

        String requestServerName = request.getScheme() + "://" + request.getServerName()
                + ":" + request.getServerPort();

        //版本前缀
        String path = ((HttpServletRequest) request).getRequestURI();
        if (path.startsWith(VERSION_PATH)) {
            int secondIndex = path.indexOf("/", 1);
            if (secondIndex > 0) {
                requestServerName += path.substring(0, secondIndex);
            }
        }

        userContext.setRequestServerName(requestServerName);

        LoginUserContext.setUserContext(userContext);
    }
}
