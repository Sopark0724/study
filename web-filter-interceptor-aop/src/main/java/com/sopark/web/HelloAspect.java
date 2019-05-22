package com.sopark.web;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
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

    @Around("execution(* com.sopark.web.*.hello())")
    public Object onPointcut(ProceedingJoinPoint pjp) throws Throwable {
        log.info("aop start @Around!!");
        Object proceed = pjp.proceed();
        log.info("aop end @Around!!");

        return proceed;
    }
}
