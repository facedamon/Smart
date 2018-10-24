package com.facedamon.smart.core.shiro.service;

import com.facedamon.smart.common.constant.Constants;
import com.facedamon.smart.common.constant.PasswordConstants;
import com.facedamon.smart.common.constant.UserConstants;
import com.facedamon.smart.common.exception.user.UserBlockedException;
import com.facedamon.smart.common.exception.user.UserDeleteException;
import com.facedamon.smart.common.exception.user.UserNotExistsException;
import com.facedamon.smart.common.exception.user.UserPasswordNotMatchException;
import com.facedamon.smart.common.utils.StringUtils;
import com.facedamon.smart.common.utils.security.ShiroUtils;
import com.facedamon.smart.project.system.user.domain.User;
import com.facedamon.smart.project.system.user.domain.UserStatus;
import com.facedamon.smart.project.system.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

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
        // TODO
        /**
         * 验证码校验
         */

        /**
         * 用户名或密码为空
         */
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            throw new UserNotExistsException();
        }

        /**
         * 密码不在指定范围内
         */
        if (password.length() < PasswordConstants.PASSWORD_MIN_LENGTH.getValue()
                || password.length() > PasswordConstants.PASSWORD_MAX_LENGTH.getValue()) {
            throw new UserPasswordNotMatchException();
        }

        /**
         * 用户民不在指定范围内
         */
        if (username.length() < UserConstants.UserName.USERNAME_MIN_LENGTH.getValue()
                || username.length() > UserConstants.UserName.USERNAME_MAX_LENGTH.getValue()) {
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
            throw new UserNotExistsException();
        }
        if (UserStatus.DELETED.getCode().equals(user.getDelFlag())) {
            throw new UserDeleteException();
        }
        if (UserStatus.DISABLED.getCode().equals(user.getStatus())) {
            throw new UserBlockedException(user.getRemark());
        }

        passwordService.validate(user, password);
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
        user.setLoginDate(new Date());
        userService.updateUserInfo(user);
    }
}
