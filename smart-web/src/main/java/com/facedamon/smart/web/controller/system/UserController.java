package com.facedamon.smart.web.controller.system;

import com.facedamon.smart.common.annotation.Log;
import com.facedamon.smart.common.base.Response;
import com.facedamon.smart.common.enums.BusinessType;
import com.facedamon.smart.framework.shiro.service.PasswordService;
import com.facedamon.smart.framework.util.ExcelUtils;
import com.facedamon.smart.framework.util.ShiroUtils;
import com.facedamon.smart.framework.web.page.TableDataInfo;
import com.facedamon.smart.system.doamin.User;
import com.facedamon.smart.system.service.IPostService;
import com.facedamon.smart.system.service.IRoleService;
import com.facedamon.smart.system.service.IUserService;
import com.facedamon.smart.web.core.base.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: 用户模块相关操作控制器
 * @Author: facedamon
 * @CreateDate: 2018/9/30 下午9:12
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/9/30 下午9:12
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Controller
@RequestMapping("/system/user")
public class UserController extends BaseController {

    private String prefix = "system/user";

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IPostService postService;

    @Autowired
    private PasswordService passwordService;

    @RequiresPermissions("system:user:view")
    @GetMapping
    public String user(){
        return prefix + "/user";
    }

    @RequiresPermissions("system:user:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(User user){
        startPage();
        List<User> list = userService.selectUserList(user);
        return getDataTable(list);
    }

    @Log(model = "用户模块",businessType = BusinessType.EXPORT)
    @RequiresPermissions("system:user:export")
    @PostMapping("/export")
    @ResponseBody
    public Response export(User user){
        List<User> users = userService.selectUserList(user);
        ExcelUtils<User> excelUtils = new ExcelUtils<>(User.class);
        return excelUtils.export(users,"user");
    }

    /**
     * 新增用户跳转页面
     * @param map
     * @return
     */
    @GetMapping("/add")
    public String add(ModelMap map){
        map.put("roles",roleService.selectRoleAll());
        map.put("posts",postService.selectPostAll());
        return prefix + "/add";
    }

    @Log(model = "用户模块",businessType = BusinessType.INSERT)
    @RequiresPermissions("system:user:add")
    @PostMapping("/add")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public Response add(User user){
        if (user.getUserId() != null && User.isAdmin(user.getUserId())){
            return Response.error("不允许修改超级管理员用户");
        }
        user.setSalt(ShiroUtils.randomSalt());
        user.setPassword(passwordService.encryptPassword(user.getLoginName(),user.getPassword(),user.getSalt()));
        user.setCreateBy(ShiroUtils.getLoginName());
        return isSuccess(userService.insertUser(user));
    }

    /**
     * 编辑用户跳转页面
     * @param userId
     * @param map
     * @return
     */
    @GetMapping("/edit/{userId}")
    public String edit(@PathVariable("userId") Long userId,ModelMap map){
        map.put("user",userService.selectUserById(userId));
        map.put("roles",roleService.selectRolesByUserId(userId));
        map.put("posts",postService.selectPostsByUserId(userId));
        return prefix + "/edit";
    }

    @Log(model = "用户模块", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:user:edit")
    @PostMapping("/edit")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public Response edit(User user){
        if (user.getUserId() != null && User.isAdmin(user.getUserId())){
            return Response.error("不允许修改超级管理员用户");
        }
        user.setUpdateBy(ShiroUtils.getLoginName());
        return isSuccess(userService.updateUser(user));
    }

    @GetMapping("/resetPwd/{userId}")
    public String resetPwd(@PathVariable("userId") Long userId,ModelMap map){
        map.put("user",userService.selectUserById(userId));
        return prefix + "/resetPwd";
    }

    @Log(model = "重置密码",businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:user:resetPwd")
    @PostMapping("/resetPwd")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public Response resetPwd(User user){
        user.setSalt(ShiroUtils.randomSalt());
        user.setPassword(passwordService.encryptPassword(user.getLoginName(),user.getPassword(),user.getSalt()));
        return isSuccess(userService.resetUserPwd(user));
    }

    @Log(model = "删除用户",businessType = BusinessType.DELETE)
    @RequiresPermissions("system:user:remove")
    @PostMapping("/remove")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public Response remove(String ids){
        try {
            return isSuccess(userService.deleteUserByIds(ids));
        } catch (Exception e) {
            return Response.error(e.getMessage());
        }
    }

    /**
     * 校验用户名是否唯一
     * @param user
     * @return
     */
    @PostMapping("/checkLoginNameUnique")
    @ResponseBody
    public String checkLoginNameUnique(User user){
        return userService.checkLoginNameUnique(user.getLoginName());
    }

    @PostMapping("/checkPhoneUnique")
    @ResponseBody
    public String checkPhoneUnique(User user){
        return userService.checkPhoneUnique(user);
    }

    @PostMapping("/checkEmailUnique")
    @ResponseBody
    public String checkEmailUnique(User user){
        return userService.checkEmailUnique(user);
    }
}
