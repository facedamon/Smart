package com.facedamon.smart.project.system.user.controller;

import com.facedamon.smart.core.web.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
* @Description:
* @Author:         facedamon
* @CreateDate:     2018/8/15 17:06
* @UpdateUser:     facedamon
* @UpdateDate:     2018/8/15 17:06
* @UpdateRemark:   修改内容
* @Version:        1.0
*/
@Controller
public class LoginController extends BaseController {

    @GetMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response){
        return "login";
    }
}
