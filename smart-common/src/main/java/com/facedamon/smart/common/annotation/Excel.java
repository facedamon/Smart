package com.facedamon.smart.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description:    自定义导出excel
 * @Author:         facedamon
 * @CreateDate:     2018/11/8 12:51
 * @UpdateUser:     facedamon
 * @UpdateDate:     2018/11/8 12:51
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Excel {

    /**
     * excel column name
     * @return
     */
    String name();

    /**
     * 提示信息
     * @return
     */
    String prompt() default "";

    /**
     * 设置只能选择不能输入的列
     * @return
     */
    String[] combo() default {};

    /**
     * 是否导出数据
     * @return
     */
    boolean containsData() default true;

    /**
     * 是否是字典
     * @return
     */
    boolean isDict() default false;

    /**
     * 字典类型
     * @return
     */
    String dictType() default "";
}
