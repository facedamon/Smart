package com.facedamon.smart.common.exception.user;

import com.facedamon.smart.common.exception.base.BaseException;

/**
 * @Description:    用户模块异常
 * @Author:         facedamon
 * @CreateDate:     2018/10/1 下午5:45
 * @UpdateUser:     facedamon
 * @UpdateDate:     2018/10/1 下午5:45
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
public class UserException extends BaseException {

    UserException(String code, Object[] arge) {
        super("user", code,arge);
    }
}
