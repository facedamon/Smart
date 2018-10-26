package com.facedamon.smart.common.annotation;

import com.facedamon.smart.common.enums.DataSourceName;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description: 自定义多数据源切换注解
 * @Author: facedamon
 * @CreateDate: 2018/8/14 16:21
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/8/14 16:21
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AspjDB {
    DataSourceName name() default DataSourceName.MASTER;
}