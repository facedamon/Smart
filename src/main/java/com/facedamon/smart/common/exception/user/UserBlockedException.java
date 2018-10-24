package com.facedamon.smart.common.exception.user;

/**
 * @Description: 用户锁定异常类
 * @Author: facedamon
 * @CreateDate: 2018/10/2 下午2:39
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/10/2 下午2:39
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public class UserBlockedException extends UserException {
    public UserBlockedException(String reason) {
        super("user.blocked", new Object[]{reason});
    }
}
