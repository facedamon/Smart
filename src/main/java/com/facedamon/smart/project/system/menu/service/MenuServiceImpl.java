package com.facedamon.smart.project.system.menu.service;

import com.facedamon.smart.common.constant.Constants;
import com.facedamon.smart.common.utils.StringUtils;
import com.facedamon.smart.common.utils.security.ShiroUtils;
import com.facedamon.smart.project.system.TreeUtils;
import com.facedamon.smart.project.system.menu.domain.Menu;
import com.facedamon.smart.project.system.menu.mapper.MenuMapper;
import com.facedamon.smart.project.system.user.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.*;

@Service
@Slf4j
public class MenuServiceImpl implements IMenuService {

    @Autowired
    private MenuMapper menuMapper;


    /**
     * 根据用户信息查询菜单
     * @param user
     * @return
     */
    @Override
    public List<Menu> selectMenuByUser(User user) {
        List<Menu> menus = new LinkedList<>();
        if (user.isAdmin()){
            menus = menuMapper.selectMenuNormalAll();
        }else{
            menus = menuMapper.selectMenusByUserId(user.getUserId());
        }
        return TreeUtils.getChildPerms(menus,0);
    }

    /**
     * 查询菜单合集
     * @param menu
     * @return
     */
    @Override
    public List<Menu> selectMenuList(Menu menu) {
        return menuMapper.selectMenuList(menu);
    }

    /**
     * 查询菜单合集
     * @return
     */
    @Override
    public List<Menu> selectMenuAll() {
        return menuMapper.selectMenuAll();
    }

    /**
     * 根据用户Id查询权限
     * @param userId
     * @return
     */
    @Override
    public Set<String> selectPermsByUserId(Long userId) {
        List<String> perms = menuMapper.selectPermsByUserId(userId);
        Set<String> permsSet = new HashSet<>();
        for (String perm : perms){
            if (StringUtils.isNotBlank(perm)){
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));//我觉得应该是:
            }
        }
        return permsSet;
    }

    @Override
    public List<Map<String, Object>> menuTreeData() {
        List<Map<String,Object>> trees = new ArrayList<>();
        List<Menu> menuList = menuMapper.selectMenuAll();
        return trees = getTrees(menuList,false,null,false);
    }

    /**
     * 查询系统所有权限
     * @return
     */
    @Override
    public Map<String, String> selectPermsAll() {
        Map<String,String> section = new LinkedHashMap<>();
        List<Menu> permissions = menuMapper.selectMenuAll();
        if (CollectionUtils.isNotEmpty(permissions)){
            for (Menu menu : permissions){
                section.put(menu.getUrl(),MessageFormat.format(Constants.PREMISSION.getValue(),menu.getPerms()));
            }
        }
        return section;
    }

    /**
     * 删除菜单管理信息
     * @param menuId
     * @return
     */
    @Override
    public int deleteMenuById(Long menuId) {
        // TODO shiro刷新该用户权限的缓存
        return menuMapper.deleteMenuById(menuId);
    }

    /**
     * 根据菜单ID查询信息
     * @param menuId
     * @return
     */
    @Override
    public Menu selectMenuById(Long menuId) {
        return menuMapper.selectMenuById(menuId);
    }

    /**
     * 查询子菜单数量
     * @param parentId
     * @return
     */
    @Override
    public int selectCountMenuByParentId(Long parentId) {
        return menuMapper.selectCountMenuByParentId(parentId);
    }

    /**
     * 查询菜单使用量
     * @param menuId
     * @return
     */
    @Override
    public int selectCountRoleMenuByMenuId(Long menuId) {
        // TODO RoleMapper
        return 0;
    }

    /**
     * 新增菜单
     * @param menu
     * @return
     */
    @Override
    public int insertMenu(Menu menu) {
        menu.setCreateBy(ShiroUtils.getLoginName());
        // TODO 刷新该用户权限缓存
        return menuMapper.insertMenu(menu);
    }

    /**
     * 更新菜单
     * @param menu
     * @return
     */
    @Override
    public int updateMenu(Menu menu) {
        menu.setCreateBy(ShiroUtils.getLoginName());
        //TODO 刷新该用户权限缓存
        return menuMapper.updateMenu(menu);
    }

    /**
     * 校验菜单名称是否唯一
     * @param menu
     * @return
     */
    @Override
    public String checkMenuNameUnique(Menu menu) {
        Long menuId = null == menu.getMenuId() ? -1L : menu.getMenuId();
        Menu next = menuMapper.checkMenuNameUnique(menu.getMenuName(),menu.getParentId());
        if (null != next && next.getMenuId().longValue() == menuId.longValue()){
            return Constants.MENU_NAME_NOT_UNIQUE.getValue();
        }
        return Constants.MENU_NAME_UNIQUE.getValue();
    }

    /**
     * 菜单列表转树形列表
     * @param menuList 菜单列表
     * @param isCheck   是否需要选中
     * @param roleMenuList 角色已存在菜单列表
     * @param permsFlag     是否需要显示权限标识
     * @return
     */
    public List<Map<String,Object>> getTrees(List<Menu> menuList,boolean isCheck,List<String> roleMenuList,boolean permsFlag){
        List<Map<String,Object>> trees = new ArrayList<>();
        for (Menu menu : menuList){
            Map<String,Object> deptMap = new HashMap<>(5);
            deptMap.put("id",menu.getMenuId());
            deptMap.put("pId",menu.getParentId());
            deptMap.put("name",transMenuName(menu,roleMenuList,permsFlag));
            deptMap.put("title",menu.getMenuName());
            if (isCheck){
                deptMap.put("checked",roleMenuList.contains(menu.getMenuId() + menu.getMenuName()));
            }else{
                deptMap.put("checked",false);
            }
            trees.add(deptMap);
        }
        return trees;
    }

    /**
     * 将菜单权限显示
     * @param menu
     * @param roleMenuList
     * @param permsFlag
     * @return
     */
    protected String transMenuName(Menu menu,List<String> roleMenuList,boolean permsFlag){
        StringBuffer sb = new StringBuffer();
        sb.append(menu.getMenuName());
        if (permsFlag){
            sb.append("<font color=\"#888\">&nbsp;&nbsp;&nbsp;" + menu.getPerms() + "</font>");
        }
        return sb.toString();
    }
}
