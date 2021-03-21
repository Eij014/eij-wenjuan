package com.eij.wenjuan.component.utils.web;

import com.eij.wenjuan.component.bean.UserContext;

/**
 * @author Eij<eij00014@gmail.com>
 * Created on 2021-02-06
 */
public class LoginUserContext {
    private static final ThreadLocal<UserContext> USER_CONTEXT_THREAD_LOCAL = new ThreadLocal<>();

    public static String getUserName() {
        return getUserContext().getUserName();
    }

    public static UserContext getUserContext() {
        return USER_CONTEXT_THREAD_LOCAL.get();
    }

    public static void setUserContext(UserContext userContext) {
        USER_CONTEXT_THREAD_LOCAL.set(userContext);
    }

    public static void remove() {
        USER_CONTEXT_THREAD_LOCAL.remove();
    }

}
