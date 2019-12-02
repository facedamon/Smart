package com.facedamon.smart.quartz.support;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * @Description: quartz model spring bean holder
 * @Author: facedamon
 * @CreateDate: 2018/11/26 17:19
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/11/26 17:19
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Service
public class ApplicationHolders implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationHolders.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static Object getBean(String name) throws BeansException {
        return applicationContext.getBean(name);
    }
}
