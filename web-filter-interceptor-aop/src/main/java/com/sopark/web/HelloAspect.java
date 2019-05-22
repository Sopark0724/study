package com.sopark.web;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class HelloAspect {

    @Before("execution(* com.sopark.web.*.hello())")
    public void onBeforeHandler(JoinPoint joinPoint) {
        log.info("aop @Before!!");
    }

    @After("execution(* com.sopark.web.*.hello())")
    public void onAfterHandler(JoinPoint joinPoint) {
        log.info("aop @After!!");
    }

    @Pointcut("execution(* com.sopark.web.*.hello())")
    public void onPointcut(JoinPoint joinPoint) {
        log.info("aop @Pointcut!!");
    }
}
