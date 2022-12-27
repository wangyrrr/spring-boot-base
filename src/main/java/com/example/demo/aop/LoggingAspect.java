package com.example.demo.aop;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * 使用 aop 切面记录请求日志信息
 * </p>
 */
@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Autowired
    private Environment environment;

    private static final String START_TIME = "request-start";

    /**
     * 切入点
     */
    @Pointcut("execution(* com.example.demo.controller.*.*(..))")
    public void executeResource() {

    }

    /**
     * 前置操作
     *
     * @param point 切入点
     */
    @Before("executeResource()")
    public void beforeLog(JoinPoint point) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();
        log.info("【URL】：{}", request.getRequestURL());
        log.info("【IP】：{}", request.getRemoteAddr());
        log.info("【Class】：{}，【Method】：{}", point.getSignature().getDeclaringTypeName(), point.getSignature().getName());
        log.info("【Payload】：{}，", JSON.toJSONString(point.getArgs()));
        Map<String, String[]> parameterMap = request.getParameterMap();
        log.info("【Parameters】：{}，", JSON.toJSONString(parameterMap));
        Long start = System.currentTimeMillis();
        request.setAttribute(START_TIME, start);
    }

    /**
     * 环绕操作
     *
     * @param point 切入点
     * @return 原方法返回值
     * @throws Throwable 异常信息
     */
    @Around("executeResource()")
    public Object aroundLog(ProceedingJoinPoint point) throws Throwable {
        Object result = point.proceed();
        log.info("【Response】：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 后置操作
     */
    @AfterReturning("executeResource()")
    public void afterReturning() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();
        String userAgent = request.getHeader("User-Agent");
        Long start = (Long) request.getAttribute(START_TIME);
        Long end = System.currentTimeMillis();
        log.info("【Time】：{}ms", end - start);
        log.info("【Environment】：{}", environment.getActiveProfiles()[0]);
        log.info("【User-Agent】：{}", userAgent);
    }
}

