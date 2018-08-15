package com.facedamon.smart.core.aspj;

import com.facedamon.smart.core.aspj.annotation.AspjDB;
import com.facedamon.smart.core.datasource.DynamicDataSourceHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Order(1)
@Component
@Slf4j
public class AspectDB {

    /**
     * 切面：所有使用AspjDB注解的方法
     */
    @Pointcut("@annotation(com.facedamon.smart.core.aspj.annotation.AspjDB)")
    public void pointCut(){

    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable{
        /**
         * 获得方法签名
         */
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        Method method = methodSignature.getMethod();
        if (null != method && method.isAnnotationPresent(AspjDB.class)){
            AspjDB datasource = method.getAnnotation(AspjDB.class);
            if (null != datasource && null != datasource.name()){
                DynamicDataSourceHolder.setDB(datasource.name());
            }
        }
        try {
            return point.proceed();
        }finally{
            DynamicDataSourceHolder.remove();
        }
    }
}
