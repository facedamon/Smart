package com.facedamon.smart.web.controller.system;

import com.facedamon.smart.common.annotation.Log;
import com.facedamon.smart.common.base.Response;
import com.facedamon.smart.common.enums.BusinessType;
import com.facedamon.smart.framework.util.ShiroUtils;
import com.facedamon.smart.system.doamin.Menu;
import com.facedamon.smart.system.doamin.Role;
import com.facedamon.smart.system.service.IMenuService;
import com.facedamon.smart.web.core.base.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Description:    菜单controller
 * @Author:         facedamon
 * @CreateDate:     2018/11/16 下午7:13
 * @UpdateUser:     facedamon
 * @UpdateDate:     2018/11/16 下午7:13
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
@Controller
@RequestMapping("/system/menu")
public class MenuController extends BaseController {

    private String prefix = "system/menu";

    @Autowired
    private IMenuService menuService;

    @RequiresPermissions("system:menu:view")
    @GetMapping
    public String menu(){
        return prefix + "/menu";
    }

    @RequiresPermissions("system:menu:list")
    @GetMapping("/list")
    @ResponseBody
    public List<Menu> menu(Menu menu){
        return menuService.selectMenuList(menu);
    }

    @GetMapping("/add/{parentId}")
    public String add(@PathVariable("parentId") Long parentId,ModelMap map){
        Menu menu = null;
        if (0L != parentId){
            menu = menuService.selectMenuById(parentId);
        } else {
            menu = Menu.builder().menuId(0L).menuName("主目录").build();
        }
        map.put("menu",menu);
        return prefix + "/add";
    }

    @Log(model = "新增部门",businessType = BusinessType.INSERT)
    @RequiresPermissions("system:menu:add")
    @PostMapping("/add")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public Response add(Menu menu){
        menu.setCreateBy(ShiroUtils.getLoginName());
        ShiroUtils.clearCachedAuthorizationInfo();
        return isSuccess(menuService.insertMenu(menu));
    }

    @GetMapping("/icon")
    public String icon(){
        return prefix + "/icon";
    }

    @Log(model = "删除菜单",businessType = BusinessType.DELETE)
    @RequiresPermissions("system:menu:remove")
    @PostMapping("/remove/{menuId}")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public Response remove(@PathVariable("menuId") Long menuId){
        if (menuService.selectCountMenuByParentId(menuId) > 0){
            return Response.error("存在子菜单，不允许删除");
        }
        if (menuService.selectCountRoleMenuByMenuId(menuId) > 0){
            return Response.error("菜单已分配角色，不允许删除");
        }
        ShiroUtils.clearCachedAuthorizationInfo();
        return isSuccess(menuService.deleteMenuById(menuId));
    }

    @GetMapping("/edit/{menuId}")
    public String edit(@PathVariable("menuId") Long menuId,ModelMap map){
        map.put("menu",menuService.selectMenuById(menuId));
        return prefix + "/edit";
    }

    @Log(model = "更新菜单",businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:menu:edit")
    @PostMapping("/edit")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public Response edit(Menu menu){
        menu.setUpdateBy(ShiroUtils.getLoginName());
        ShiroUtils.clearCachedAuthorizationInfo();
        return isSuccess(menuService.updateMenu(menu));
    }

    /**
     * 选择菜单树
     */
    @GetMapping("/selectMenuTree/{menuId}")
    public String selectMenuTree(@PathVariable("menuId") Long menuId, ModelMap mmap) {
        mmap.put("menu", menuService.selectMenuById(menuId));
        return prefix + "/tree";
    }

    /**
     * 加载所有菜单列表树
     */
    @GetMapping("/menuTreeData")
    @ResponseBody
    public List<Map<String, Object>> menuTreeData(Role role) {
        List<Map<String, Object>> tree = menuService.menuTreeData();
        return tree;
    }

    @PostMapping("/checkMenuNameUnique")
    @ResponseBody
    public String checkMenuNameUnique(Menu menu) {
        return menuService.checkMenuNameUnique(menu);
    }

    /**
     * 加载角色菜单列表树
     */
    @GetMapping("/roleMenuTreeData")
    @ResponseBody
    public List<Map<String, Object>> roleMenuTreeData(Role role) {
        return menuService.roleMenuTreeData(role);
    }


}
