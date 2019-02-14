package com.learn.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by hy on 2019/2/13.
 */
@Aspect
@Component
public class LogAspect {
    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);
    @Before("execution(* com.learn.controller.IndexController.*(..))")
    public void beforeMethod(){
        logger.info("this is before method");

 }
    @After("execution(* com.learn.controller.IndexController.*(..))")

    public void afterMethod(){
        logger.info("this is after method");

    }
}
