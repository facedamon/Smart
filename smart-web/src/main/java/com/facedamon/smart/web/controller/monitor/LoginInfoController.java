package com.facedamon.smart.web.controller.monitor;

import com.facedamon.smart.common.annotation.Log;
import com.facedamon.smart.common.base.Response;
import com.facedamon.smart.common.enums.BusinessType;
import com.facedamon.smart.framework.web.page.TableDataInfo;
import com.facedamon.smart.system.domain.Logininfor;
import com.facedamon.smart.system.service.ILogininforService;
import com.facedamon.smart.web.core.base.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static com.facedamon.smart.common.base.Response.success;

/**
 * @Description: 访问历史controller
 * @Author: facedamon
 * @CreateDate: 2018/11/26 10:16
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/11/26 10:16
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Controller
@RequestMapping("/monitor/logininfor")
public class LoginInfoController extends BaseController {

    private String prefix = "monitor/logininfor";

    @Autowired
    private ILogininforService logininforService;

    @RequiresPermissions("monitor:logininfor:view")
    @GetMapping()
    public String logininfor() {
        return prefix + "/logininfor";
    }

    @RequiresPermissions("monitor:logininfor:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Logininfor logininfor) {
        startPage();
        List<Logininfor> logininfors = logininforService.selectLogininforList(logininfor);
        return getDataTable(logininfors);
    }

    @RequiresPermissions("monitor:logininfor:remove")
    @Log(model = "删除登陆日志", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public Response remove(String ids) {
        return isSuccess(logininforService.deleteLogininforByIds(ids));
    }

    @RequiresPermissions("monitor:logininfor:remove")
    @Log(model = "清空登陆日志", businessType = BusinessType.CLEAN)
    @PostMapping("/clean")
    @ResponseBody
    public Response clean() {
        logininforService.cleanLogininfo();
        return success();
    }
}
