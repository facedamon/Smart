package com.facedamon.smart.system.mapper;

import com.facedamon.smart.system.domain.RoleMenu;

import java.util.List;

/**
 * @Description: 角色-菜单 1->N
 * @Author: facedamon
 * @CreateDate: 2018/9/30 下午10:51
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/9/30 下午10:51
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public interface RoleMenuMapper {

    /**
     * 通过角色ID删除角色菜单关系
     *
     * @param RoleId
     * @return
     */
    int deleteRoleMenuByRoleId(Long RoleId);

    /**
     * 批量删除角色菜单关系
     *
     * @param ids
     * @return
     */
    int deleteRoleMenu(Long[] ids);

    /**
     * 查询菜单使用量
     *
     * @param menuId
     * @return
     */
    int selectCountRoleMenuByMenuId(Long menuId);

    /**
     * 批量新增角色菜单
     *
     * @param roleMenuList
     * @return
     */
    int batchRoleMenu(List<RoleMenu> roleMenuList);
}