package com.github.bjlhx15.springaop.interceptor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class MyIntercepter {
    private static final Logger logger = LoggerFactory.getLogger(MyIntercepter.class);
    @Pointcut("execution(public * com.github.bjlhx15.springaop.service.MyTestService.doSomething1*(..))")
    public void doSomethingPointcut(){};

    @Before("doSomethingPointcut()")
    public void auth(JoinPoint pjp) throws Throwable{
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod(); //获取被拦截的方法
        String methodName = method.getName(); //获取被拦截的方法名
        logger.info("权限认证：调用方法为：{}", methodName);
    };

    @AfterReturning(value = "doSomethingPointcut()", returning = "returnVal")
    public void logNormal(JoinPoint pjp, Object returnVal) throws Throwable{
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod(); //获取被拦截的方法
        String methodName = method.getName(); //获取被拦截的方法名
        logger.info("正常返回记日志：调用方法为：{}；返回结果为:{}", methodName, returnVal);
    };

    @AfterThrowing(value = "doSomethingPointcut()", throwing = "e")
    public void logThrowing(JoinPoint pjp, Throwable e) throws Throwable{
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod(); //获取被拦截的方法
        String methodName = method.getName(); //获取被拦截的方法名
        logger.info("抛出异常记日志：调用方法为：{}；异常信息为:{}", methodName, e.getMessage());
    };

    @After(value = "doSomethingPointcut()")
    public void afterall(JoinPoint pjp) throws Throwable{
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod(); //获取被拦截的方法
        String methodName = method.getName(); //获取被拦截的方法名
        logger.info("方法调用完成：调用方法为：{}", methodName);
    }

    @Around("doSomethingPointcut()")
    public Object timer(ProceedingJoinPoint pjp) throws Throwable{
        long beginTime = System.currentTimeMillis();
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod(); //获取被拦截的方法
        String methodName = method.getName(); //获取被拦截的方法名
        logger.info("计时切面：请求开始，方法：{}", methodName);

        Object result = null;
        try {
            // 一切正常的情况下，继续执行被拦截的方法
            result = pjp.proceed();
        } catch (Throwable e) {
            logger.info("exception: ", e);
        }
        long endTime = System.currentTimeMillis();
        logger.info("计时切面：请求结束，方法：{}，执行时间：{}", methodName, (endTime-beginTime));
        return result;
    }
}
