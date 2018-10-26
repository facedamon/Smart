package com.facedamon.smart.framework.web.exception.user;


/**
 * @Description: 验证码异常
 * @Author: facedamon
 * @CreateDate: 2018/10/15 下午5:05
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/10/15 下午5:05
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public class CaptchaException extends UserException {

    CaptchaException() {
        super("user.jcaptcha.error", null);
    }
}