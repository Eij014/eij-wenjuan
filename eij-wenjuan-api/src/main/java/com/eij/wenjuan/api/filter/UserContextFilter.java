package com.eij.wenjuan.api.filter;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.eij.wenjuan.component.bean.UserContext;
import com.eij.wenjuan.component.utils.web.HttpRequestUtils;
import com.eij.wenjuan.component.utils.web.LoginUserContext;

/**
 * @author Eij<eij00014@gmail.com>
 * Created on 2021-02-05
 */
@Component
public class UserContextFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(UserContextFilter.class);

    @Autowired(required = false)
    private UserContextFilter.WhiteList whiteList;

    public abstract static class WhiteList {

        protected abstract Set<String> getPatternSet();

        private List<Pattern> patternList = new LinkedList<>();

        void init() {
            Set<String> patternSet = getPatternSet();
            for (String regex : patternSet) {
                Pattern pattern = Pattern.compile(regex);
                patternList.add(pattern);
            }
        }

        public boolean contains(String path) {
            for (Pattern pattern : patternList) {
                if (pattern.matcher(path).find()) {
                    return true;
                }
            }
            return false;
        }
    }

    @Override
    public void init(FilterConfig filterConfig) {
        logger.info("UserContextFilter.init");
        if (whiteList != null) {
            whiteList.init();
        }
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String path = ((HttpServletRequest) request).getServletPath();
        if (whiteList != null && whiteList.contains(path)) {
            chain.doFilter(request, response);
            return;
        }

        String token = HttpRequestUtils.getCookie((HttpServletRequest) request, HttpRequestUtils.TOKEN_IDENTIFY);
        if (Objects.isNull(token) || Objects.isNull(HttpRequestUtils.getAccountFromToken(token))) {
            logger.warn("UserContextFilter.doFilter: UNAUTHORIZED");
            ((HttpServletResponse) response).setStatus(HttpStatus.UNAUTHORIZED.value());
        } else {
                String userName = HttpRequestUtils.getAccountFromToken(token);
                request.setAttribute("userName", userName);
                setUserContext(request);
                long startTime = System.currentTimeMillis();

                long filterEndTime = System.currentTimeMillis() - startTime;
                logger.info("path:{},userName:{},cost:{}ms", path, userName, filterEndTime);
                chain.doFilter((HttpServletRequest) request, (HttpServletResponse) response);
                return;
            }
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
//        if (path.startsWith(VERSION_PATH)) {
//            int secondIndex = path.indexOf("/", 1);
//            if (secondIndex > 0) {
//                requestServerName += path.substring(0, secondIndex);
//            }
//        }

        userContext.setRequestServerName(requestServerName);

        LoginUserContext.setUserContext(userContext);
    }

    @Override
    public void destroy() {
        LoginUserContext.remove();
    }
}
