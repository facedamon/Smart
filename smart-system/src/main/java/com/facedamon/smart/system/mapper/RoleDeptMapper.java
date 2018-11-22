package com.facedamon.smart.system.mapper;

import com.facedamon.smart.system.domain.RoleDept;

import java.util.List;

/**
 * @Description: 角色-部门关系 1->N
 * @Author: facedamon
 * @CreateDate: 2018/9/30 下午10:41
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/9/30 下午10:41
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public interface RoleDeptMapper {

    /**
     * 通过角色ID删除角色和部门关系
     *
     * @param roleId
     * @return
     */
    int deleteRoleDeptByRoleId(Long roleId);

    /**
     * 批量删除角色部门关系
     *
     * @param ids
     * @return
     */
    int deleteRoleDept(Long[] ids);

    /**
     * 查询部门使用量
     *
     * @param deptId
     * @return
     */
    int selectCountRoleDeptByDeptId(Long deptId);

    /**
     * 批量新增角色部门
     *
     * @param roleDeptList
     * @return
     */
    int batchRoleDept(List<RoleDept> roleDeptList);
}