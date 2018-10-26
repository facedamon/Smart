package com.facedamon.smart.framework.shiro.service;

import com.facedamon.smart.framework.web.exception.user.UserPasswordNotMatchException;
import com.facedamon.smart.framework.web.exception.user.UserPasswordRetryLimitExceedException;
import com.facedamon.smart.system.doamin.User;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description: 登录密码服务
 * @Author: facedamon
 * @CreateDate: 2018/10/1 上午6:06
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/10/1 上午6:06
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Component
public class PasswordService {

    @Autowired
    private CacheManager cacheManager;

    /**
     * 登录记录缓存
     */
    private Cache<String, AtomicInteger> loginRecordCache;

    @Value(value = "${user.password.maxRetryCount}")
    private String maxRetryCount;

    @PostConstruct
    public void init() {
        loginRecordCache = cacheManager.getCache("loginRecordCache");
    }


    /**
     * 登录校验
     *
     * @param user
     * @param password
     */
    public void validate(User user, String password) {
        String loginName = user.getLoginName();
        AtomicInteger retryCount = loginRecordCache.get(loginName);

        /**
         * 第一次登录
         */
        if (null == retryCount) {
            retryCount = new AtomicInteger(0);
            loginRecordCache.put(loginName, retryCount);
        }

        /**
         * 输错密码达到上线
         */
        /*if (retryCount.incrementAndGet() > Integer.valueOf(maxRetryCount).intValue()) {
            // TODO ASYNC
            throw new UserPasswordRetryLimitExceedException(Integer.valueOf(maxRetryCount).intValue());
        }*/

        /**
         * 密码输入错误
         */
        if (!match(user, password)) {
            // TODO ASYNC
            loginRecordCache.put(loginName, retryCount);
            throw new UserPasswordNotMatchException();
        } else {
            cleanLoginRecordCache(loginName);
        }
    }


    /**
     * 清空指定usern缓存
     *
     * @param username
     */
    private void cleanLoginRecordCache(String username) {
        loginRecordCache.remove(username);
    }

    /**
     * 密码匹配
     *
     * @param user
     * @param newPassword
     * @return
     */
    private boolean match(User user, String newPassword) {
        return user.getPassword().equals(encryptPassword(user.getLoginName(), newPassword, user.getSalt()));
    }

    /**
     * 加密
     *
     * @param username
     * @param password
     * @param salt
     * @return
     */
    public String encryptPassword(String username, String password, String salt) {
        return new Md5Hash(username + password + salt).toHex().toString();
    }
}