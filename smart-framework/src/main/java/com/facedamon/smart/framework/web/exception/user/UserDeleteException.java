package com.facedamon.smart.framework.web.exception.user;

/**
 * @Description: 用户账号已被删除
 * @Author: facedamon
 * @CreateDate: 2018/10/2 下午2:30
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/10/2 下午2:30
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public class UserDeleteException extends UserException {
    public UserDeleteException() {
        super("user.password.delete", null);
    }
}