package com.facedamon.smart.framework.web.service;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;

/**
 * @Description:    JS 调用thymeleaf实现按钮权限可见
 * @Author:         facedamon
 * @CreateDate:     2018/10/29 16:39
 * @UpdateUser:     facedamon
 * @UpdateDate:     2018/10/29 16:39
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
@Service("permission")
public class PermissionService {

    /**
     * 按钮是否有permission的权限
     * css 显示配置
     * @param permission
     * @return
     */
    public String hasPermi(String permission){
        return isPermittedOperator(permission) ? "" : "hidden";
    }

    private boolean isPermittedOperator(String permission) {
        return SecurityUtils.getSubject().isPermitted(permission);
    }
}
