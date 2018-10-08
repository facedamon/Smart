package com.facedamon.smart.core.shiro.realm;

import com.facedamon.smart.common.utils.security.ShiroUtils;
import com.facedamon.smart.core.shiro.service.LoginService;
import com.facedamon.smart.project.system.menu.service.IMenuService;
import com.facedamon.smart.project.system.role.service.IRoleService;
import com.facedamon.smart.project.system.user.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

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

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IMenuService menuService;

    private LoginService loginService;

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
            roles = roleService.selectRoleKeys(user.getUserId());
            menus = menuService.selectPermsByUserId(user.getUserId());

            info.setRoles(roles);
            info.setStringPermissions(menus);
        }
        return info;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = new UsernamePasswordToken();
        String username = token.getUsername();
        String password = "";

        if (null != token.getPassword()){
            password = String.valueOf(token.getPassword());
        }

        User user = null;
        user = loginService.login(username,password);
        // TODO
        return null;
    }

    /**
     * 清理授权
     */
    public void cleanCacheduthorizationInfo(){
        this.clearCachedAuthenticationInfo(SecurityUtils.getSubject().getPrincipals());
    }
}
