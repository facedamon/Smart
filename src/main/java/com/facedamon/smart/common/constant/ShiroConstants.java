package com.facedamon.smart.common.constant;


/**
 * @Description:    shiro final enum
 * @Author:         facedamon
 * @CreateDate:     2018/10/6 下午5:55
 * @UpdateUser:     facedamon
 * @UpdateDate:     2018/10/6 下午5:55
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
public enum ShiroConstants {

    /**
     * 当前登录的用户
     */
    CURRENT_USER ("currentUser"),

    /**
     * 用户名
     */
    CURRENT_USERNAME ("username"),

    /**
     * 消息key
     */
    MESSAGE ("message"),

    /**
     * 错误key
     */
    ERROR ("errorMsg"),

    /**
     * 当前在线会话
     */
    ONLINE_SESSION ("online_session"),

    /**
     * 验证码key
     */
    CURRENT_CAPTCHA ("captcha"),

    /**
     * 验证码开关
     */
    CURRENT_ENABLED ("captchaEnabled"),

    /**
     * 验证码开关
     */
    CURRENT_TYPE ("captchaType"),

    /**
     * 验证码
     */
    CURRENT_VALIDATECODE ("validateCode"),

    /**
     * 验证码错误
     */
    CAPTCHA_ERROR ("captchaError");

    private String value;

    ShiroConstants(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
