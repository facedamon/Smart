package com.facedamon.smart.framework.web.exception.user;

/**
 * @Description: 用户密码不正确或不符合规范异常类
 * @Author: facedamon
 * @CreateDate: 2018/10/1 下午5:47
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/10/1 下午5:47
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public class UserPasswordNotMatchException extends UserException {

    public UserPasswordNotMatchException() {
        super("user.password.not.match", null);
    }
}