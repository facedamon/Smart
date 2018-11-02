package com.facedamon.smart.common.annotation;

import com.facedamon.smart.common.enums.BusinessType;
import com.facedamon.smart.common.enums.OperatorType;

import java.lang.annotation.*;

/**
 * @Description:    自定义操作日志记录注解同步到OperLog数据库
 * @Author:         facedamon
 * @CreateDate:     2018/10/29 10:08
 * @UpdateUser:     facedamon
 * @UpdateDate:     2018/10/29 10:08
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
@Target({ElementType.METHOD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

    /**
     * 模块
     * @return
     */
    String model() default "";

    /**
     * 功能
     * @return
     */
    BusinessType businessType() default BusinessType.OTHER;

    /**
     * 操作人类别
     * @return
     */
    OperatorType operatorType() default OperatorType.MANAGE;

    /**
     * 是否保存请求参数
     * @return
     */
    boolean isSaveRequestData() default true;
}
