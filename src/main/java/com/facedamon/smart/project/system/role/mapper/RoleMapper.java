package com.facedamon.smart.project.system.role.mapper;

import com.facedamon.smart.project.system.role.domain.Role;

import java.util.List;

public interface RoleMapper {

    /**
     * 根据条件分页查询角色数据
     * @param role
     * @return
     */
    List<Role> selectRoleList(Role role);

    /**
     * 根据用户Id查询角色
     * @param userId
     * @return
     */
    List<Role> selectRolesByUserId(Long userId);

    /**
     * 通过角色Id查询角色
     * @param roleId
     * @return
     */
    Role selectRoleById(Long roleId);

    /**
     * 通过角色Id删除角色
     * @param roleId
     * @return
     */
    int deleteRoleById(Long roleId);

    /**
     * 批量删除角色用户信息
     * @param ids
     * @return
     */
    int deleteRoleByIds(Long[] ids);

    /**
     * 修改角色信息
     * @param role
     * @return
     */
    int updateRole(Role role);

    /**
     * 新增角色信息
     * @param role
     * @return
     */
    int insertRole(Role role);

    /**
     * 校验角色名称是否唯一
     * @return
     */
    Role checkRoleNameUnique(String roleName);

    /**
     * 校验角色权限是否唯一
     * @param roleKey
     * @return
     */
    Role checkRoleKeyUnique(String roleKey);
}
