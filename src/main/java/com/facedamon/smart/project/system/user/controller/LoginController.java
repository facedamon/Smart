package com.facedamon.smart.project.system.user.controller;

import com.facedamon.smart.common.http.HttpUtils;
import com.facedamon.smart.core.web.controller.BaseController;
import com.facedamon.smart.core.web.domain.Response;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description:
 * @Author: facedamon
 * @CreateDate: 2018/8/15 17:06
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/8/15 17:06
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Controller
public class LoginController extends BaseController {

    @GetMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response) {
        if (HttpUtils.isAjaxRequest(request)) {
            HttpUtils.renderString(response, "{\"code\":\"1\",\"msg\":\"未登录或登录超时。请重新登录\"}");
            return null;
        }
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public Response login(String username, String password, Boolean rememberMe) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();

        try {
            subject.login(token);
            return Response.success();
        } catch (AuthenticationException e) {
            return Response.error("用户名或密码错误");
        }
    }
}
