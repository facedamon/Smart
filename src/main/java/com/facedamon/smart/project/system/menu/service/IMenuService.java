package com.facedamon.smart.project.system.menu.service;

import com.facedamon.smart.project.system.menu.domain.Menu;
import com.facedamon.smart.project.system.user.domain.User;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IMenuService {

    /**
     * 根据用户ID查询菜单
     * @param user
     * @return
     */
     List<Menu> selectMenuByUser(User user);

    /**
     * 查询系统菜单列表
     * @param menu
     * @return
     */
     List<Menu> selectMenuList(Menu menu);

    /**
     * 查询菜单合集
     * @return
     */
     List<Menu> selectMenuAll();

    /**
     * 根据用户ID查询权限
     * @param userId
     * @return
     */
     Set<String> selectPermsByUserId(Long userId);

    // List<Map<String,Object>> roleMenuTreeData();

    /**
     * 查询所有菜单信息
     * @return
     */
     List<Map<String,Object>> menuTreeData();

    /**
     * 查询系统所有权限
     * @return
     */
     Map<String,String> selectPermsAll();

    /**
     * 删除菜单管理信息
     * @param menuId
     * @return
     */
     int deleteMenuById(Long menuId);

    /**
     * 根据菜单ID查询信息
     * @param menuId
     * @return
     */
     Menu selectMenuById(Long menuId);

    /**
     * 查询菜单数量
     * @param parentId
     * @return
     */
     int selectCOuntMenuByParentId(Long parentId);

    /**
     * 查询菜单使用数量
     * @param menuId
     * @return
     */
     int selectCountRoleMenuByMenuId(Long menuId);

    /**
     * 新增保存菜单信息
     * @param menu
     * @return
     */
     int insertMenu(Menu menu);

    /**
     * 修改保存菜单信息
     * @param menu
     * @return
     */
     int updateMenu(Menu menu);

    /**
     * 校验菜单名称是否唯一
     * @param menu
     * @return
     */
     String checkMenuNameUnique(Menu menu);

}
