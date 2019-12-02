package com.facedamon.smart.web.controller.tool;

import com.facedamon.smart.common.utils.StringUtils;
import com.facedamon.smart.web.core.base.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description: Swagger2 接口controller
 * @Author: facedamon
 * @CreateDate: 2018/11/26 12:15
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/11/26 12:15
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Controller
@RequestMapping("/tool/swagger")
public class SwaggerController extends BaseController {

    @RequiresPermissions("tool:swagger:view")
    @GetMapping()
    public String swagger() {
        return StringUtils.format("redirect:{}", "/swagger-ui.html");
    }
}
