package com.github.bjlhx15.springaop.interceptor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class MyIntercepter2 {
    private static final Logger logger = LoggerFactory.getLogger(MyIntercepter2.class);

    @Pointcut("@annotation(com.github.bjlhx15.springaop.anno.TestTimer)")
    public void timerPointcut(){};

    @Pointcut("@within(com.github.bjlhx15.springaop.anno.TestLogger)")
    public void recordLogPointcut(){};

    @Before("recordLogPointcut()")
    public void log(JoinPoint pjp) throws Throwable{
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod(); //获取被拦截的方法
        String methodName = method.getName(); //获取被拦截的方法名
        logger.info("开始记日志：调用方法为：{}", methodName);
    }

    @Around("timerPointcut()")
    public Object timer(ProceedingJoinPoint pjp) throws Throwable{
        long beginTime = System.currentTimeMillis();
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod(); //获取被拦截的方法
        String methodName = method.getName(); //获取被拦截的方法名

        logger.info("请求开始，方法：{}", methodName);

        Object result = null;

        try {
            // 一切正常的情况下，继续执行被拦截的方法
            result = pjp.proceed();
        } catch (Throwable e) {
            logger.info("exception: ", e);
        }
        long endTime = System.currentTimeMillis();
        logger.info("请求结束，方法：{}，执行时间：{}", methodName, (endTime-beginTime));
        return result;
    }
}
