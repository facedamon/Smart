package com.facedamon.smart.system.service;

import com.facedamon.smart.system.doamin.Role;

import java.util.List;
import java.util.Set;

/**
 * @Description: 角色service
 * @Author: facedamon
 * @CreateDate: 2018/9/30 下午10:55
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/9/30 下午10:55
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public interface IRoleService {

    /**
     * 根据角色分页查找角色信息
     *
     * @param role
     * @return
     */
    List<Role> selectRoleList(Role role);

    /**
     * 根据用户ID查询权限信息
     *
     * @param userId
     * @return
     */
    Set<String> selectRoleKeys(Long userId);

    /**
     * 根据用户ID查询角色
     *
     * @param userId
     * @return
     */
    List<Role> selectRoleByUserId(Long userId);

    /**
     * 查询所有角色
     *
     * @return
     */
    List<Role> selectRoleAll();

    /**
     * 通过角色ID查询角色
     *
     * @param roleId
     * @return
     */
    Role selectRoleById(Long roleId);

    /**
     * 通过角色ID删除角色
     *
     * @param roleId
     * @return
     */
    boolean deleteRoleById(Long roleId);

    /**
     * 批量删除角色信息
     *
     * @param ids
     * @return
     * @throws Exception
     */
    int deleteRoleByIds(String ids) throws Exception;

    /**
     * 新增角色
     *
     * @param role
     * @return
     */
    int insertRole(Role role);

    /**
     * 更新角色
     *
     * @param role
     * @return
     */
    int updateRole(Role role);

    /**
     * 更新数据权限信息
     *
     * @param role
     * @return
     */
    int updateRule(Role role);

    /**
     * 校验角色名称唯一
     *
     * @param role
     * @return
     */
    String checkRoleNameUnique(Role role);

    /**
     * 校验角色权限唯一
     *
     * @param role
     * @return
     */
    String checkRoleKeyUnique(Role role);

    /**
     * 通过角色ID查询角色使用量
     *
     * @param roleId
     * @return
     */
    int selectUserRoleByRoleId(Long roleId);

}