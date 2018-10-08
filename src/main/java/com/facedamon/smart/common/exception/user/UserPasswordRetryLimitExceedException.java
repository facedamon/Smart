package com.facedamon.smart.common.exception.user;

/**
 * @Description:    用户错误最大次数异常类
 * @Author:         facedamon
 * @CreateDate:     2018/10/2 下午2:29
 * @UpdateUser:     facedamon
 * @UpdateDate:     2018/10/2 下午2:29
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
public class UserPasswordRetryLimitExceedException extends UserException {
    UserPasswordRetryLimitExceedException(int retryLimitCount) {
        super("user.password.retry.limit.exceed", new Object[]{retryLimitCount});
    }
}
