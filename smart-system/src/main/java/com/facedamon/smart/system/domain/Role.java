package com.facedamon.smart.system.domain;

import com.facedamon.smart.common.base.BaseEntity;
import lombok.Builder;
import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * @Description: 角色bean
 * @Author: facedamon
 * @CreateDate: 2018/9/30 下午9:06
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/9/30 下午9:06
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Data
@Builder
@Alias("Role")
public class Role extends BaseEntity {

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色权限
     */
    private String roleKey;

    /**
     * 角色排序
     */
    private String roleSort;

    /**
     * 数据范围（1：所有数据权限；2：自定数据权限）
     */
    private String dataScope;

    /**
     * 角色状态（0正常 1停用）
     */
    private String status;

    /**
     * 用户是否存在此角色标识 默认不存在
     */
    private boolean flag = false;

    /**
     * 菜单组
     */
    private Long[] menuIds;

    /**
     * 部门组（数据权限）
     */
    private Long[] deptIds;
}