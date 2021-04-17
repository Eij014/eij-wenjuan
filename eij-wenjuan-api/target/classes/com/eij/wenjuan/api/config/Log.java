package com.eij.wenjuan.api.config;

import org.apache.commons.lang3.time.StopWatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Eij<eij00014@gmail.com>
 * Created on 2021-03-13
 */
@Aspect
@Component
public class Log {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(Log.class);


    /**拦截所有controller包下的方法*/
    @Pointcut("execution(* com.eij.wenjuan.api.controller..*.*(..))")
    private void controllerMethod() {
    }

    /**拦截所有service包下的方法*/
    @Pointcut("execution(* com.eij.wenjuan.component.dao..*.*(..))")
    private void daoMethod() {
    }

    /**拦截所有service包下的方法*/
    @Pointcut("execution(* com.eij.wenjuan.component.service..*.*(..))")
    private void serviceMethod() {
    }


    @Before(value = "controllerMethod()")
    private void controllerBefore() {
    }

    @Before(value = "daoMethod()")
    private void daoBefore() {
    }

    @Before(value = "serviceMethod()")
    private void serviceBefore() {
    }

    /**
     * 打印所有日志
     */
    @Around("controllerMethod() || daoMethod() || serviceMethod()")
    public Object doAround(ProceedingJoinPoint point) throws Throwable {
        StopWatch watcher = new StopWatch();
        Object result = null;
        try {
            // 执行完方法的返回值：调用proceed()方法，就会触发切入点方法执行
            result = point.proceed(); // result的值就是被拦截方法的返回值
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
