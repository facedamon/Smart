package com.facedamon.smart.core.shiro.realm;

import com.facedamon.smart.common.utils.security.ShiroUtils;
import com.facedamon.smart.project.system.user.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description:    自定义用户realm
 * @Author:         facedamon
 * @CreateDate:     2018/9/30 下午9:14
 * @UpdateUser:     facedamon
 * @UpdateDate:     2018/9/30 下午9:14
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
@Slf4j
public class UserRealm extends AuthorizingRealm {

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = ShiroUtils.getUser();
        //角色列表
        Set<String> roles = new HashSet<>();
        //功能列表
        Set<String> menus = new HashSet<>();
        //授权
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        if (user.isAdmin()){
            info.addRole("admin");
            info.addStringPermission("*:*:*");
        }else{

        }
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        return null;
    }
}
