package com.facedamon.smart.web.controller.system;

import com.facedamon.smart.common.annotation.Log;
import com.facedamon.smart.common.base.Response;
import com.facedamon.smart.common.enums.BusinessType;
import com.facedamon.smart.common.utils.Md5Utils;
import com.facedamon.smart.framework.shiro.service.PasswordService;
import com.facedamon.smart.framework.util.ShiroUtils;
import com.facedamon.smart.system.doamin.User;
import com.facedamon.smart.system.service.IDictDataService;
import com.facedamon.smart.system.service.IUserService;
import com.facedamon.smart.web.core.base.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:    个人信息
 * @Author:         facedamon
 * @CreateDate:     2018/11/15 下午7:30
 * @UpdateUser:     facedamon
 * @UpdateDate:     2018/11/15 下午7:30
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
@Controller
@RequestMapping("/system/user/profile")
@Slf4j
public class ProfileController extends BaseController {

    private String prefix = "system/user/profile";

    @Autowired
    private IUserService userService;

    @Autowired
    private PasswordService passwordService;

    @Autowired
    private IDictDataService dictDataService;

    @GetMapping
    public String profile(ModelMap map){
        User user = ShiroUtils.getUser();
        user.setSex(dictDataService.selectDictLabel("sys_user_sex",user.getSex()));
        map.put("user",user);
        map.put("roleGroup",userService.selectUserRoleGroup(user.getUserId()));
        map.put("postGroup",userService.selectUserPostGroup(user.getUserId()));
        return prefix + "/profile";
    }

    @GetMapping("/checkPassword")
    @ResponseBody
    public boolean checkPassword(String password){
        User user = ShiroUtils.getUser();
        String encrypt = new Md5Hash(user.getLoginName() + password + user.getSalt()).toHex().toString();

        return user.getPassword().equals(encrypt) ? true : false;
    }

    @GetMapping("/resetPwd/{userId}")
    public String resetPwd(@PathVariable("userId") Long userId, ModelMap map){
        map.put("user",userService.selectUserById(userId));
        return prefix + "/resetPwd";
    }

    @Log(model = "重置密码",businessType = BusinessType.UPDATE)
    @PostMapping("/resetPwd")
    @ResponseBody
    public Response resetPwd(User user){
        user.setSalt(ShiroUtils.randomSalt());
        user.setPassword(passwordService.encryptPassword(user.getLoginName(), user.getPassword(), user.getSalt()));

        if (userService.resetUserPwd(user) <= 0){
            return Response.error();
        } else {
            ShiroUtils.setUser(userService.selectUserById(user.getUserId()));
            return Response.success();
        }
    }

    @GetMapping("/edit/{userId}")
    public String edit(@PathVariable("userId") Long userId,ModelMap map){
        map.put("user", userService.selectUserById(userId));
        return prefix + "/edit";
    }

    @Log(model = "更新个人信息", businessType = BusinessType.UPDATE)
    @PostMapping("/update")
    @ResponseBody
    public Response update(User user)
    {
        if (userService.updateUserInfo(user) > 0)
        {
            ShiroUtils.setUser((userService.selectUserById(user.getUserId())));
            return Response.success();
        }
        return Response.error();
    }
}
