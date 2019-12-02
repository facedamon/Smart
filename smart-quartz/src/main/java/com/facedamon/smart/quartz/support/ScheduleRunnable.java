package com.facedamon.smart.quartz.support;

import com.facedamon.smart.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

/**
 * @Description: 核心任务
 * @Author: facedamon
 * @CreateDate: 2018/11/26 17:40
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/11/26 17:40
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public class ScheduleRunnable implements Runnable {

    private static final Logger log = LoggerFactory.getLogger("quartz");

    private Object target;
    private Method method;
    private String params;

    public ScheduleRunnable(String beanName, String methodName, String params) throws NoSuchMethodException, SecurityException {
        this.target = ApplicationHolders.getBean(beanName);
        this.params = params;

        if (StringUtils.isNotBlank(params)) {
            this.method = target.getClass().getDeclaredMethod(methodName, String.class);
        } else {
            this.method = target.getClass().getDeclaredMethod(methodName);
        }
    }

    @Override
    public void run() {
        try {
            ReflectionUtils.makeAccessible(method);
            if (StringUtils.isNotBlank(params)) {
                method.invoke(target, params);
            } else {
                method.invoke(target);
            }
        } catch (Exception e) {
            log.error("执行定时任务异常: ", e);
        }
    }
}
