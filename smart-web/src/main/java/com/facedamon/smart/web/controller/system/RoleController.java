package com.facedamon.smart.web.controller.system;

import com.facedamon.smart.common.annotation.Log;
import com.facedamon.smart.common.base.Response;
import com.facedamon.smart.common.enums.BusinessType;
import com.facedamon.smart.framework.util.ExcelUtils;
import com.facedamon.smart.framework.util.ShiroUtils;
import com.facedamon.smart.framework.web.page.TableDataInfo;
import com.facedamon.smart.system.domain.Role;
import com.facedamon.smart.system.service.IRoleService;
import com.facedamon.smart.web.core.base.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description:    角色controller
 * @Author:         facedamon
 * @CreateDate:     2018/11/16 下午4:36
 * @UpdateUser:     facedamon
 * @UpdateDate:     2018/11/16 下午4:36
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
@Controller
@RequestMapping("/system/role")
public class RoleController extends BaseController {

    private String prefix = "system/role";

    @Autowired
    private IRoleService roleService;

    @RequiresPermissions("system:role:view")
    @GetMapping
    public String role(){
        return prefix + "/role";
    }

    @RequiresPermissions("system:role:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Role role){
        startPage();
        List<Role> roles = roleService.selectRoleList(role);
        return getDataTable(roles);
    }

    @GetMapping("/add")
    public String add(){
        return prefix + "/add";
    }

    @Log(model = "新增角色",businessType = BusinessType.INSERT)
    @RequiresPermissions("system:role:add")
    @PostMapping("/add")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public Response add(Role role){
        role.setCreateBy(ShiroUtils.getLoginName());
        ShiroUtils.clearCachedAuthorizationInfo();
        return isSuccess(roleService.insertRole(role));
    }

    @GetMapping("/edit/{roleId}")
    public String edit(@PathVariable("roleId") Long roleId, ModelMap map){
        map.put("role",roleService.selectRoleById(roleId));
        return prefix + "/edit";
    }

    @Log(model = "修改角色",businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:role:edit")
    @PostMapping("/edit")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public Response edit(Role role){
        role.setUpdateBy(ShiroUtils.getLoginName());
        ShiroUtils.clearCachedAuthorizationInfo();
        return isSuccess(roleService.updateRole(role));
    }

    @GetMapping("/rule/{roleId}")
    public String rule(@PathVariable("roleId") Long roleId,ModelMap map){
        map.put("role",roleService.selectRoleById(roleId));
        return prefix + "/rule";
    }

    @Log(model = "修改数据权限",businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:role:edit")
    @PostMapping("/rule")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public Response rule(Role role){
        role.setUpdateBy(ShiroUtils.getLoginName());
        return isSuccess(roleService.updateRule(role));
    }

    @Log(model = "删除角色",businessType = BusinessType.DELETE)
    @RequiresPermissions("system:role:remove")
    @PostMapping("/remove")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public Response remove(String ids){
        try {
            return isSuccess(roleService.deleteRoleByIds(ids));
        }catch (Exception e){
            return Response.error(e.getMessage());
        }
    }

    @PostMapping("/checkRoleNameUnique")
    @ResponseBody
    public String checkRoleNameUnique(Role role){
        return roleService.checkRoleNameUnique(role);
    }

    @PostMapping("/checkRoleKeyUnique")
    @ResponseBody
    public String checkRoleKeyUnique(Role role){
        return roleService.checkRoleKeyUnique(role);
    }

    /**
     * 选择菜单树
     */
    @GetMapping("/selectMenuTree")
    public String selectMenuTree() {
        return prefix + "/tree";
    }

    @Log(model = "导出角色",businessType = BusinessType.EXPORT)
    @RequiresPermissions("system:role:export")
    @PostMapping("/export")
    @ResponseBody
    public Response export(Role role){
        List<Role> roles = roleService.selectRoleList(role);
        ExcelUtils utils = new ExcelUtils<Role>(Role.class);
        return utils.export(roles,"role");
    }
}
