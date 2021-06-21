package com.springboot.demo.aspect.aspects;

import com.springboot.demo.aspect.annotation.Customer;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: zjhan
 * @Date: 2021/6/18 15:28
 * @Description:
 **/
@Aspect
@Component
@Slf4j
public class NamePrinter {
    @Pointcut("@annotation(com.springboot.demo.aspect.annotation.Customer)")
    public void weblogWithAnnotation() {

    }


    /**
     * 直接获取字段值，当字段值未泛型，并获取泛型里的字段，会出现类型不匹配问题
     * @param joinpoint
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @Before("weblogWithAnnotation()")
    public void doBefore(JoinPoint joinpoint) throws NoSuchFieldException, IllegalAccessException {
        MethodSignature signature = (MethodSignature) joinpoint.getSignature();
        Method method = signature.getMethod();
        List<String> paramNames = Arrays.asList(signature.getParameterNames());
        Customer customer = method.getAnnotation(Customer.class);
        Object[] args = joinpoint.getArgs();
        String[] name = customer.name().split("\\.");
        String descFormatter = customer.desc().replaceAll("\\{}", "%s");
        if (name.length > 1) {
            int argIndex = paramNames.indexOf(name[0]);
            String fieldName = name[1];
            Object arg = args[argIndex];
            log.info("classType: {}", arg.getClass());
            Field field =  arg.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            log.info("fileName: {}", field.get(arg).toString());
            log.info("desc: {}", String.format(descFormatter, field.get(arg)));
        } else {
            log.info(String.valueOf(args[paramNames.indexOf(name[0])]));
            log.info("desc: {}", String.format(descFormatter, args[paramNames.indexOf(name[0])]));
        }

        log.info("customer: {}" ,customer.name());
    }

    /**
     * 使用反射获取值
     * @param joinPoint
     * @param result
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     */
    @AfterReturning(value = "weblogWithAnnotation()", returning = "result")
    public void doAfterReturning(JoinPoint joinPoint, Object result) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        List<String> paramNames = Arrays.asList(signature.getParameterNames());
        Customer customer = method.getAnnotation(Customer.class);
        Object[] args = joinPoint.getArgs();
        String[] name = customer.name().split("\\.");
        String descFormatter = customer.desc().replaceAll("\\{}", "%s");
//        Field data = result.getClass().getDeclaredField("data");
//        data.setAccessible(true);
//        Field rN = data.get(result).getClass().getDeclaredField("name");
//        log.info("rn: {}", rN.toString());
        // 使用获取方法获取泛型中属性值
        Object data = result.getClass().getMethod("getData").invoke(result);
        Object dataName = data.getClass().getMethod("getName").invoke(data);
        log.info("r: name: {}", dataName);
//        rN.setAccessible(true);
//        log.info("r: name: {}", rN.get(data).toString());
        if (name.length > 1) {
            int argIndex = paramNames.indexOf(name[0]);
            String fieldName = name[1];
            Object arg = args[argIndex];
            log.info("classType: {}", arg.getClass());
            Field field =  arg.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            log.info("fileName: {}", field.get(arg).toString());
            log.info("desc: {}", String.format(descFormatter, field.get(arg)));
        } else {
            log.info(String.valueOf(args[paramNames.indexOf(name[0])]));
            log.info("desc: {}", String.format(descFormatter, args[paramNames.indexOf(name[0])]));
        }

        log.info("customer: {}" ,customer.name());
    }
}
