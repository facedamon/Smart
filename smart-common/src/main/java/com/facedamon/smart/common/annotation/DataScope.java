package com.facedamon.smart.common.annotation;

import java.lang.annotation.*;

/**
 * @Description: 数据权限过滤注解
 * @Author: facedamon
 * @CreateDate: 2018/10/26 11:39
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/10/26 11:39
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataScope {

    /**
     * 表别名
     *
     * @return
     */
    String tableAlias() default "";
}
