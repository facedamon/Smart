package com.facedamon.smart.system.domain;

import lombok.Builder;
import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * @Description: 角色-部门对应关系类 1->N
 * @Author: facedamon
 * @CreateDate: 2018/9/30 下午9:06
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/9/30 下午9:06
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Data
@Builder
@Alias("RoleDept")
public class RoleDept {

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 部门ID
     */
    private Long deptId;
}