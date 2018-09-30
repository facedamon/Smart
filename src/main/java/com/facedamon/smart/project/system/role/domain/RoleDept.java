package com.facedamon.smart.project.system.role.domain;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("RoleDept")
public class RoleDept {

    /** 角色ID */
    private Long roleId;

    /** 部门ID */
    private Long deptId;
}
