package com.facedamon.smart.framework.web.exception.user;

/**
 * @Description: 角色锁定异常
 * @Author: facedamon
 * @CreateDate: 2018/10/15 下午5:11
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/10/15 下午5:11
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public class RoleBlockedException extends UserException {
    RoleBlockedException(String reason) {
        super("role.blocked", new Object[]{reason});
    }
}