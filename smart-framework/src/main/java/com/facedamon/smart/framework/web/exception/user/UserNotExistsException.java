package com.facedamon.smart.framework.web.exception.user;

/**
 * @Description: 用户不存在异常
 * @Author: facedamon
 * @CreateDate: 2018/10/15 下午5:07
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/10/15 下午5:07
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public class UserNotExistsException extends UserException {

    public UserNotExistsException() {
        super("user.not.exists", null);
    }
}