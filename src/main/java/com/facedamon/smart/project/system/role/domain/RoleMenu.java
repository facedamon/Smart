package com.facedamon.smart.project.system.role.domain;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("RoleMenu")
public class RoleMenu {

    /** 角色ID */
    private Long roleId;

    /** 菜单ID */
    private Long menuId;
}
