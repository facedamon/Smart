package com.facedamon.smart.core.shiro.service;

import com.facedamon.smart.common.constant.Constants;
import com.facedamon.smart.common.utils.security.ShiroUtils;
import com.facedamon.smart.project.system.user.domain.User;
import com.facedamon.smart.project.system.user.service.IUserService;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Description:    登录校验服务
 * @Author:         facedamon
 * @CreateDate:     2018/10/1 上午6:05
 * @UpdateUser:     facedamon
 * @UpdateDate:     2018/10/1 上午6:05
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
@Component
public class LoginService {

    @Autowired
    private PasswordService passwordService;

    @Autowired
    private IUserService userService;

    public User login(String username,String password){
        // TODO
        return null;
    }

    /**
     * 校验email
     * @param email
     * @return
     */
    private boolean maybeEmail(String email){
        return email.matches(Constants.EMAIL_PATTERN.getValue()) ? true : false;
    }

    /**
     * 校验电话
     * @param phoneNumber
     * @return
     */
    private boolean maybePhoneNumber(String phoneNumber){
        return phoneNumber.matches(Constants.MOBILE_PHONE_NUMBER_PATTERN.getValue()) ? true : false;
    }

    /**
     * 记录登录者信息
     * @param user
     */
    public void recordLoginInfo(User user){
        user.setLoginIp(ShiroUtils.getIp());
        user.setLoginDate(new Date());
        userService.updateUserInfo(user);
    }
}
