package com.facedamon.smart.web.controller.system;

import com.facedamon.smart.common.annotation.Log;
import com.facedamon.smart.common.base.Response;
import com.facedamon.smart.common.enums.BusinessType;
import com.facedamon.smart.framework.util.ShiroUtils;
import com.facedamon.smart.system.doamin.Dept;
import com.facedamon.smart.system.doamin.Role;
import com.facedamon.smart.system.service.IDeptService;
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
 * @Description:    部门controller
 * @Author:         facedamon
 * @CreateDate:     2018/11/6 11:28
 * @UpdateUser:     facedamon
 * @UpdateDate:     2018/11/6 11:28
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
@Controller
@RequestMapping("/system/dept")
public class DeptController extends BaseController {

    private String prefix = "system/dept";

    @Autowired
    private IDeptService deptService;

    @RequiresPermissions("system:dept:view")
    @GetMapping
    public String dept(){
        return prefix + "/dept";
    }

    @RequiresPermissions("system:dept:list")
    @GetMapping("/list")
    @ResponseBody
    public List<Dept> list(Dept dept){
        return deptService.selectDeptList(dept);
    }

    @GetMapping("/add/{parentId}")
    public String add(@PathVariable("parentId") Long parentId,ModelMap map){
        map.put("dept",deptService.selectDeptById(parentId));
        return prefix + "/add";
    }

    @Log(model = "新增部门",businessType = BusinessType.INSERT)
    @RequiresPermissions("system:dept:add")
    @PostMapping("/add")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public Response add(Dept dept){
        dept.setCreateBy(ShiroUtils.getLoginName());
        return isSuccess(deptService.insertDept(dept));
    }

    @GetMapping("/edit/{deptId}")
    public String edit(@PathVariable("deptId") Long deptId,ModelMap map){
        Dept dept = deptService.selectDeptById(deptId);
        if (null != dept && 100L == deptId){
            dept.setParentName("无父节点");
        }
        map.put("dept",dept);
        return prefix + "/edit";
    }

    @Log(model = "修改部门",businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:dept:edit")
    @PostMapping("/edit")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public Response edit(Dept dept){
        dept.setUpdateBy(ShiroUtils.getLoginName());
        return isSuccess(deptService.updateDept(dept));
    }

    @Log(model = "删除部门", businessType = BusinessType.DELETE)
    @RequiresPermissions("system:dept:remove")
    @PostMapping("/remove/{deptId}")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public Response remove(@PathVariable("deptId") Long deptId){
        if (deptService.selectDeptCount(deptId) > 0){
            return Response.error("存在下级部门，不允许删除");
        }
        if (deptService.checkDeptExistUser(deptId)){
            return Response.error("部门存在用户，不允许删除");
        }
        return isSuccess(deptService.deleteDeptById(deptId));
    }

    @GetMapping("/selectDeptTree/{deptId}")
    public String selectDeptTree(@PathVariable("deptId") Long deptId,ModelMap map){
        map.put("dept",deptService.selectDeptById(deptId));
        return prefix + "/tree";
    }

    @GetMapping("/treeData")
    @ResponseBody
    public List<Map<String,Object>> treeData(){
        return deptService.selectDeptTree();
    }

    /**
     * 加载角色部门（数据权限）列表树
     */
    @GetMapping("/roleDeptTreeData")
    @ResponseBody
    public List<Map<String, Object>> deptTreeData(Role role) {
        return deptService.roleDeptTreeData(role);
    }

}
