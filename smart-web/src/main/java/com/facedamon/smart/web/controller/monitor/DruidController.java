package com.facedamon.smart.web.controller.monitor;

import com.facedamon.smart.common.utils.StringUtils;
import com.facedamon.smart.web.core.base.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description:    Druid 监控
 * @Author:         facedamon
 * @CreateDate:     2018/11/26 12:37
 * @UpdateUser:     facedamon
 * @UpdateDate:     2018/11/26 12:37
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
@Controller
@RequestMapping("/monitor/datasource")
public class DruidController extends BaseController {

    private String prefix = "/monitor/druid";

    @RequiresPermissions("monitor:datasource:view")
    @GetMapping()
    public String index() {
        return StringUtils.format("redirect:{}",prefix + "/index");
    }
}
