package com.facedamon.smart.system.service.impl;

import com.facedamon.smart.common.annotation.DataScope;
import com.facedamon.smart.common.constant.Constants;
import com.facedamon.smart.common.support.Convert;
import com.facedamon.smart.system.domain.Role;
import com.facedamon.smart.system.domain.RoleDept;
import com.facedamon.smart.system.domain.RoleMenu;
import com.facedamon.smart.system.mapper.RoleDeptMapper;
import com.facedamon.smart.system.mapper.RoleMapper;
import com.facedamon.smart.system.mapper.RoleMenuMapper;
import com.facedamon.smart.system.mapper.UserRoleMapper;
import com.facedamon.smart.system.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Description: 角色服务
 * @Author: facedamon
 * @CreateDate: 2018/9/30 下午11:20
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/9/30 下午11:20
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Autowired
    private RoleDeptMapper roleDeptMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    /**
     * 根据角色条件分页查询角色信息
     *
     * @param role
     * @return
     */
    @Override
    @DataScope(tableAlias = "u")
    public List<Role> selectRoleList(Role role) {
        return roleMapper.selectRoleList(role);
    }

    /**
     * 根据用户ID查询权限信息
     *
     * @param userId
     * @return
     */
    @Override
    public Set<String> selectRoleKeys(Long userId) {
        List<Role> perms = roleMapper.selectRolesByUserId(userId);
        Set<String> permsSet = new HashSet<>();
        for (Role perm : perms) {
            if (null != perm) {
                permsSet.addAll(Arrays.asList(perm.getRoleKey().trim().split(",")));
            }
        }
        return permsSet;
    }

    /**
     * 根据用户ID查询角色信息
     *
     * @param userId
     * @return
     */
    @Override
    public List<Role> selectRolesByUserId(Long userId) {
        List<Role> perms = roleMapper.selectRolesByUserId(userId);
        List<Role> roles = selectRoleAll();
        for (Role role : roles) {
            for (Role perm : perms) {
                if (perm.getRoleId().longValue() == role.getRoleId().longValue()) {
                    role.setFlag(true);
                    break;
                }
            }
        }
        return roles;
    }

    /**
     * 查询所有角色
     *
     * @return
     */
    @Override
    public List<Role> selectRoleAll() {
        return roleMapper.selectRoleList(Role.builder().build());
    }

    /**
     * 通过角色ID查询角色
     *
     * @param roleId
     * @return
     */
    @Override
    public Role selectRoleById(Long roleId) {
        return roleMapper.selectRoleById(roleId);
    }

    /**
     * 通过制定角色ID删除角色
     *
     * @param roleId
     * @return
     */
    @Override
    public boolean deleteRoleById(Long roleId) {
        return roleMapper.deleteRoleById(roleId) > 0 ? true : false;
    }

    @Override
    public int deleteRoleByIds(String ids) throws Exception {
        Long[] roleIds = Convert.toLongArray(ids);
        for (Long roleId : roleIds) {
            Role role = roleMapper.selectRoleById(roleId);

            if (selectUserRoleByRoleId(roleId) > 0) {
                throw new Exception(String.format("%1$s已分配，不能删除", role.getRoleName()));
            }
        }
        return roleMapper.deleteRoleByIds(roleIds);
    }

    /**
     * 新增角色
     *
     * @param role
     * @return
     */
    @Override
    public int insertRole(Role role) {
        roleMapper.insertRole(role);
        return insertRoleMenu(role);
    }

    /**
     * 更新角色
     *
     * @param role
     * @return
     */
    @Override
    public int updateRole(Role role) {
        roleMapper.updateRole(role);
        /**
         * 角色菜单关系发生变化
         */
        roleMenuMapper.deleteRoleMenuByRoleId(role.getRoleId());
        return insertRoleMenu(role);
    }

    /**
     * 更新数据权限信息
     *
     * @param role
     * @return
     */
    @Override
    public int updateRule(Role role) {
        roleMapper.updateRole(role);
        roleDeptMapper.deleteRoleDeptByRoleId(role.getRoleId());
        return insertRoleDept(role);
    }

    /**
     * 校验角色名称是否唯一
     *
     * @param role
     * @return
     */
    @Override
    public String checkRoleNameUnique(Role role) {
        Long roleId = role.getRoleId() == null ? -1L : role.getRoleId();
        Role next = roleMapper.checkRoleNameUnique(role.getRoleName());
        if (null != next && next.getRoleId().longValue() != roleId.longValue()) {
            return Constants.ROLE_NAME_NOT_UNIQUE.getValue();
        }
        return Constants.ROLE_NAME_UNIQUE.getValue();
    }

    /**
     * 校验角色权限是否唯一
     *
     * @param role
     * @return
     */
    @Override
    public String checkRoleKeyUnique(Role role) {
        Long roleId = role.getRoleId() == null ? -1L : role.getRoleId();
        Role next = roleMapper.checkRoleKeyUnique(role.getRoleKey());
        if (null != next && next.getRoleId().longValue() != roleId.longValue()) {
            return Constants.ROLE_KEY_NOT_UNIQUE.getValue();
        }
        return Constants.ROLE_KEY_UNIQUE.getValue();
    }

    @Override
    public int selectUserRoleByRoleId(Long roleId) {
        return userRoleMapper.countUserRoleByRoleId(roleId);
    }

    /**
     * 新增角色菜单信息
     *
     * @param role
     * @return
     */
    private int insertRoleMenu(Role role) {
        int rows = 1;
        List<RoleMenu> roleMenus = new ArrayList<>();
        for (Long menuId : role.getMenuIds()) {
            roleMenus.add(RoleMenu.builder().menuId(menuId).roleId(role.getRoleId()).build());
        }
        if (!roleMenus.isEmpty()) {
            rows = roleMenuMapper.batchRoleMenu(roleMenus);
        }
        return rows;
    }

    /**
     * 新增角色部门信息
     *
     * @param role
     * @return
     */
    private int insertRoleDept(Role role) {
        int rows = 1;
        List<RoleDept> roleDepts = new ArrayList<>();
        for (Long deptId : role.getDeptIds()) {
            roleDepts.add(RoleDept.builder().deptId(deptId).roleId(role.getRoleId()).build());
        }
        if (!roleDepts.isEmpty()) {
            rows = roleDeptMapper.batchRoleDept(roleDepts);
        }
        return rows;
    }


}