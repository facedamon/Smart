package com.facedamon.smart.common.exception.user;

/**
 * @Description:    用户错误记数异常类
 * @Author:         facedamon
 * @CreateDate:     2018/10/2 下午2:27
 * @UpdateUser:     facedamon
 * @UpdateDate:     2018/10/2 下午2:27
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
public class UserPasswordRetryLimitException extends UserException {
    UserPasswordRetryLimitException(int retryLimitCount) {
        super("ser.password.retry.limit.count", new Object[]{retryLimitCount});
    }
}
