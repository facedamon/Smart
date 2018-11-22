package com.facedamon.smart.framework.util;

import com.facedamon.smart.framework.shiro.realm.UserRealm;
import com.facedamon.smart.system.domain.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;

/**
 * @Description: shiro工具类
 * @Author: facedamon
 * @CreateDate: 2018/9/30 下午9:07
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/9/30 下午9:07
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public class ShiroUtils {

    /**
     * 获取访问对象
     *
     * @return
     */
    private static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    /**
     * 获取当前登录者会话
     *
     * @return
     */
    public static Session getSession() {
        return getSubject().getSession();
    }

    /**
     * 注销登录对象
     */
    public static void logout() {
        getSubject().logout();
    }

    /**
     * 获取登录用户ID
     *
     * @return
     */
    public static Long getUserId() {
        return getUser().getUserId().longValue();
    }

    /**
     * 获取登录用户名
     *
     * @return
     */
    public static String getLoginName() {
        return getUser().getLoginName();
    }

    /**
     * 获取登录用户主机地址
     *
     * @return
     */
    public static String getIp() {
        return getSubject().getSession().getHost();
    }

    /**
     * 获取登录者会话ID
     *
     * @return
     */
    public static String getSessionId() {
        return String.valueOf(getSubject().getSession().getId());
    }

    /**
     * 获取登录者信息
     *
     * @return
     */
    public static User getUser() {
        User user = null;
        Object obj = getSubject().getPrincipal();
        if (null != obj) {
            user = new User();
            BeanUtils.copyProperties(obj, user);
        }
        return user;
    }

    /**
     * 更新登录者信息
     *
     * @param user
     */
    public static void setUser(User user) {
        Subject subject = getSubject();
        PrincipalCollection principalCollection = subject.getPrincipals();
        String realName = principalCollection.getRealmNames().iterator().next();
        PrincipalCollection newPrincipalCollection = new SimplePrincipalCollection(user, realName);
        subject.runAs(newPrincipalCollection);
    }

    /**
     * 刷新用户缓存
     */
    public static void clearCachedAuthorizationInfo() {
        RealmSecurityManager manager = (RealmSecurityManager) SecurityUtils.getSecurityManager();
        UserRealm userRealm = (UserRealm) manager.getRealms().iterator().next();
        userRealm.cleanCacheduthorizationInfo();
    }

    /**
     * 生成随机盐
     */
    public static String randomSalt()
    {
        // 一个Byte占两个字节，此处生成的3字节，字符串长度为6
        SecureRandomNumberGenerator secureRandom = new SecureRandomNumberGenerator();
        String hex = secureRandom.nextBytes(3).toHex();
        return hex;
    }
}