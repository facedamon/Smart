package com.facedamon.smart.framework.shiro.realm;

import com.facedamon.smart.framework.shiro.service.LoginService;
import com.facedamon.smart.framework.util.ShiroUtils;
import com.facedamon.smart.framework.web.exception.user.*;
import com.facedamon.smart.system.domain.User;
import com.facedamon.smart.system.service.IMenuService;
import com.facedamon.smart.system.service.IRoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description: 自定义用户realm
 * @Author: facedamon
 * @CreateDate: 2018/9/30 下午9:14
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/9/30 下午9:14
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Slf4j
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IMenuService menuService;

    @Autowired
    private LoginService loginService;

    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = ShiroUtils.getUser();
        //角色列表
        Set<String> roles = new HashSet<>(15);
        //功能列表
        Set<String> menus = new HashSet<>(15);
        //授权
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        if (user.isAdmin()) {
            info.addRole("admin");
            info.addStringPermission("*:*:*");
        } else {
            roles = roleService.selectRoleKeys(user.getUserId());
            menus = menuService.selectPermsByUserId(user.getUserId());

            info.setRoles(roles);
            info.setStringPermissions(menus);
        }
        return info;
    }

    /**
     * 认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        String password = "";

        if (null != token.getPassword()) {
            password = String.valueOf(token.getPassword());
        }

        User user = null;

        try {
            user = loginService.login(username, password);
        } catch (CaptchaException e) {
            throw new AuthenticationException(e.getMessage(), e);
        } catch (UserNotExistsException e) {
            throw new UnknownAccountException(e.getMessage(), e);
        } catch (UserPasswordNotMatchException e) {
            throw new IncorrectCredentialsException(e.getMessage(), e);
        } catch (UserPasswordRetryLimitExceedException e) {
            throw new ExcessiveAttemptsException(e.getMessage(), e);
        } catch (UserBlockedException e) {
            throw new LockedAccountException(e.getMessage(), e);
        } catch (RoleBlockedException e) {
            throw new LockedAccountException(e.getMessage(), e);
        } catch (Exception e) {
            log.info("对用户[" + username + "]进行登录验证..验证未通过{}", e.getMessage());
            throw new AuthenticationException(e.getMessage(), e);
        }

        return new SimpleAuthenticationInfo(user, password, getName());
    }

    /**
     * 清理授权
     */
    public void cleanCacheduthorizationInfo() {
        this.clearCachedAuthenticationInfo(SecurityUtils.getSubject().getPrincipals());
    }
}