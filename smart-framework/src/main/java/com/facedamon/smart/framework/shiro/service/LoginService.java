package com.facedamon.smart.framework.shiro.service;

import com.facedamon.smart.common.constant.Constants;
import com.facedamon.smart.common.constant.PasswordConstants;
import com.facedamon.smart.common.constant.ShiroConstants;
import com.facedamon.smart.common.constant.UserConstants;
import com.facedamon.smart.common.enums.UserStatus;
import com.facedamon.smart.common.utils.DateUtils;
import com.facedamon.smart.common.utils.StringUtils;
import com.facedamon.smart.framework.syn.AsyncFactory;
import com.facedamon.smart.framework.util.MessageUtils;
import com.facedamon.smart.framework.util.ServletUtils;
import com.facedamon.smart.framework.util.ShiroUtils;
import com.facedamon.smart.framework.web.exception.user.*;
import com.facedamon.smart.system.doamin.User;
import com.facedamon.smart.system.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description: 登录校验服务
 * @Author: facedamon
 * @CreateDate: 2018/10/1 上午6:05
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/10/1 上午6:05
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Component
public class LoginService {

    @Autowired
    private PasswordService passwordService;

    @Autowired
    private IUserService userService;

    public User login(String username, String password) {

        /**
         * 验证码校验
         */
        if (StringUtils.isNotBlank((String)ServletUtils.getRequest().getAttribute(ShiroConstants.CURRENT_CAPTCHA.getValue()))){
            AsyncFactory.INSTANCE.recordLogininfor(username,Constants.LOGIN_FAIL.getValue(),MessageUtils.message("user.jcaptcha.error"));
            throw new CaptchaException();
        }
        /**
         * 用户名或密码为空
         */
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            AsyncFactory.INSTANCE.recordLogininfor(username,Constants.LOGIN_FAIL.getValue(),MessageUtils.message("not.null"));
            throw new UserNotExistsException();
        }

        /**
         * 密码不在指定范围内
         */
        if (password.length() < PasswordConstants.PASSWORD_MIN_LENGTH.getValue()
                || password.length() > PasswordConstants.PASSWORD_MAX_LENGTH.getValue()) {
            AsyncFactory.INSTANCE.recordLogininfor(username,Constants.LOGIN_FAIL.getValue(),MessageUtils.message("user.password.not.match"));
            throw new UserPasswordNotMatchException();
        }

        /**
         * 用户民不在指定范围内
         */
        if (username.length() < UserConstants.UserName.USERNAME_MIN_LENGTH.getValue()
                || username.length() > UserConstants.UserName.USERNAME_MAX_LENGTH.getValue()) {
            AsyncFactory.INSTANCE.recordLogininfor(username,Constants.LOGIN_FAIL.getValue(),MessageUtils.message("user.password.not.match"));
            throw new UserPasswordNotMatchException();
        }

        /**
         * 查询
         */
        User user = userService.selectUserByLoginName(username);

        if (null == user && maybePhoneNumber(username)) {
            user = userService.selectUserByPhoneNumber(username);
        }
        if (null == user && maybeEmail(username)) {
            user = userService.selectUserByEmail(username);
        }
        if (null == user) {
            AsyncFactory.INSTANCE.recordLogininfor(username,Constants.LOGIN_FAIL.getValue(),MessageUtils.message("user.not.exists"));
            throw new UserNotExistsException();
        }
        if (UserStatus.DELETED.getCode().equals(user.getDelFlag())) {
            AsyncFactory.INSTANCE.recordLogininfor(username,Constants.LOGIN_FAIL.getValue(),MessageUtils.message("user.password.delete"));
            throw new UserDeleteException();
        }
        if (UserStatus.DISABLE.getCode().equals(user.getStatus())) {
            AsyncFactory.INSTANCE.recordLogininfor(username,Constants.LOGIN_FAIL.getValue(),MessageUtils.message("user.blocked"));
            throw new UserBlockedException(user.getRemark());
        }

        passwordService.validate(user, password);
        AsyncFactory.INSTANCE.recordLogininfor(username,Constants.LOGIN_SUCCESS.getValue(),MessageUtils.message("user.login.success"));
        recordLoginInfo(user);

        return user;
    }

    /**
     * 校验email
     *
     * @param email
     * @return
     */
    private boolean maybeEmail(String email) {
        return email.matches(Constants.EMAIL_PATTERN.getValue()) ? true : false;
    }

    /**
     * 校验电话
     *
     * @param phoneNumber
     * @return
     */
    private boolean maybePhoneNumber(String phoneNumber) {
        return phoneNumber.matches(Constants.MOBILE_PHONE_NUMBER_PATTERN.getValue()) ? true : false;
    }

    /**
     * 记录登录者信息
     *
     * @param user
     */
    public void recordLoginInfo(User user) {
        user.setLoginIp(ShiroUtils.getIp());
        user.setLoginDate(DateUtils.getNowDate());
        userService.updateUserInfo(user);
    }
}