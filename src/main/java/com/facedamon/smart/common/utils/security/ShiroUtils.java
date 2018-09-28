package com.facedamon.smart.common.utils.security;

import com.facedamon.smart.project.system.user.domain.User;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;

import java.lang.reflect.InvocationTargetException;

public class ShiroUtils {

    private static Subject getSubject(){
        return SecurityUtils.getSubject();
    }

    public static Session getSession(){
        return getSubject().getSession();
    }

    public static void logout(){
        getSubject().logout();
    }

    public static User getUser(){
        User user = null;
        Object obj = getSubject().getPrincipal();
        if (null != obj){
            user = new User();
            try {
                BeanUtils.copyProperties(user,obj);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    public static void setUser(User user){
        Subject subject = getSubject();
        PrincipalCollection principalCollection = subject.getPrincipals();
        String realName = principalCollection.getRealmNames().iterator().next();
        PrincipalCollection newPrincipalCollection = new SimplePrincipalCollection(user,realName);
        subject.runAs(newPrincipalCollection);
    }
}
