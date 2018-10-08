package com.facedamon.smart.core.shiro.web.session;

import org.apache.shiro.session.mgt.SessionValidationScheduler;

/**
 * @Description:    自定义session调度器 定时检查session
 * @Author:         facedamon
 * @CreateDate:     2018/10/7 上午11:18
 * @UpdateUser:     facedamon
 * @UpdateDate:     2018/10/7 上午11:18
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
public class SpringSessionValidationScheduler implements SessionValidationScheduler {

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public void enableSessionValidation() {

    }

    @Override
    public void disableSessionValidation() {

    }
}
