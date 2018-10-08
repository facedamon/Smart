package com.facedamon.smart.common.utils;

import org.springframework.context.MessageSource;

/**
 * @Description:    全局message
 * @Author:         facedamon
 * @CreateDate:     2018/10/1 下午5:36
 * @UpdateUser:     facedamon
 * @UpdateDate:     2018/10/1 下午5:36
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
public class MessageUtils {

    public static String message(String code, Object... args){
        MessageSource messageSource = ApplicationHolder.getBean(MessageSource.class);
        return messageSource.getMessage(code,args,null);
    }
}
