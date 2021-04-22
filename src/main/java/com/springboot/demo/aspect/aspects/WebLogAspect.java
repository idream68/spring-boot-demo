package com.springboot.demo.aspect.aspects;

import com.google.gson.Gson;
import com.springboot.demo.aspect.annotation.WebLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;


@Aspect
@Component
@Slf4j
public class WebLogAspect {
    @Pointcut("execution(public * com.springboot.demo.aspect.controller..*.*(..))")
    public void weblogWithClassName() {

    }

    @Pointcut("@annotation(com.springboot.demo.aspect.annotation.WebLog)")
    public void weblogWithAnnotation() {

    }


    @Before("weblogWithClassName()")
    public void doBefore(JoinPoint joinpoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        log.info("====================Start==================");
        log.info("Url:          {}", request.getRequestURL().toString());
        log.info("HTTP Method:  {}", request.getMethod());
        log.info("Class Method: {}.{}", joinpoint.getSignature().getDeclaringTypeName(), joinpoint.getSignature().getName());
        log.info("IP:           {}", request.getRemoteAddr());
        log.info("Request Args: {}", Arrays.toString(joinpoint.getArgs()));
    }

    @After("weblogWithClassName()")
    public void doAfter() {
        log.info("===================End==============");
    }

    @Around("weblogWithClassName()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.nanoTime();
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        assert attributes != null;
//        HttpServletRequest request = attributes.getRequest();
//        log.info("====================Start==================");
//        log.info("Url:           {}", request.getRequestURL().toString());
//        log.info("HTTP Method:   {}", request.getMethod());
//        log.info("Class Method:  {}.{}", proceedingJoinPoint.getSignature().getDeclaringTypeName(), proceedingJoinPoint.getSignature().getName());
//        log.info("IP:            {}", request.getRemoteAddr());
//        log.info("Request Args:  {}", Arrays.toString(proceedingJoinPoint.getArgs()));
        Object result = proceedingJoinPoint.proceed();
        // 打印出参
        log.info("Response Args: {}", new Gson().toJson(result));
        // 执行耗时
        log.info("className Time-Consuming: {} ms", (System.nanoTime() - startTime) / 1000000);
        log.info("=====================End===================");
        return result;
    }

    @Around("weblogWithAnnotation()")
    public Object doAroundWithAnnotation(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.nanoTime();
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        assert attributes != null;
//        HttpServletRequest request = attributes.getRequest();
        log.info("====================annotation Start==================");
//        log.info("Url:           {}", request.getRequestURL().toString());
//        log.info("HTTP Method:   {}", request.getMethod());
//        log.info("Class Method:  {}.{}", proceedingJoinPoint.getSignature().getDeclaringTypeName(), proceedingJoinPoint.getSignature().getName());
//        log.info("IP:            {}", request.getRemoteAddr());
//        log.info("Request Args:  {}", Arrays.toString(proceedingJoinPoint.getArgs()));

        // 获取注解中参数
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method signatureMethod = signature.getMethod();
        WebLog webLog = signatureMethod.getAnnotation(WebLog.class);
        log.info("annotation value: {}", webLog.value());
        Object result = proceedingJoinPoint.proceed();

        // 打印出参
        log.info("annotation Response Args: {}", new Gson().toJson(result));
        // 执行耗时
        log.info("annotation Time-Consuming: {} ms", (System.nanoTime() - startTime) / 1000000);
        log.info("=====================annotation End===================");
        return result;
    }

}
