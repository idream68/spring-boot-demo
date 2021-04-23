package com.springboot.demo.multi_datasource.aspect;

import com.springboot.demo.multi_datasource.config.DynamicDataSource;
import com.springboot.demo.multi_datasource.annotation.MyDataSource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@Slf4j
public class DataSourceAspect {
    /**
     * 设置切入点
     * 只有调用@MyDataSource注解的方法才会触发around
     */
    @Pointcut("@annotation(com.springboot.demo.multi_datasource.annotation.MyDataSource)")
    public void dataSourcePointCut() {
    }

    /**
     * 截取使用MyDataSource注解的方法，切换指定数据源
     * 环绕切面：是（前置&后置&返回&异常）通知的结合体，更像是动态代理的整个过程
     * @param point
     */
    @Around("dataSourcePointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        log.info("execute DataSourceAspect around=========>"+method.getName());
        // 1. 获取自定义注解MyDataSource，查看是否配置指定数据源名称
        MyDataSource dataSource = method.getAnnotation(MyDataSource.class);
        if(dataSource == null){
            // 1.1 使用默认数据源
            DynamicDataSource.setDataSource("primary");
        }else {
            // 1.2 使用指定名称数据源
            DynamicDataSource.setDataSource(dataSource.dataSource());
            log.info("使用指定名称数据源=========>"+dataSource.dataSource());
        }
        try {
            return point.proceed();
        } finally {
            // 后置处理 - 恢复默认数据源
            DynamicDataSource.clearDataSource();
        }
    }
}
