package com.facedamon.smart.web.controller.system;

import com.facedamon.smart.common.annotation.Log;
import com.facedamon.smart.common.base.Response;
import com.facedamon.smart.common.enums.BusinessType;
import com.facedamon.smart.framework.util.ShiroUtils;
import com.facedamon.smart.framework.web.page.TableDataInfo;
import com.facedamon.smart.system.domain.Config;
import com.facedamon.smart.system.service.IConfigService;
import com.facedamon.smart.web.core.base.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: 参数配置
 * @Author: facedamon
 * @CreateDate: 2018/11/19 下午8:07
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/11/19 下午8:07
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Controller
@RequestMapping("/system/config")
public class ConfigController extends BaseController {

    private String prefix = "system/config";

    @Autowired
    private IConfigService configService;

    @RequiresPermissions("system:config:view")
    @GetMapping
    public String config() {
        return prefix + "/config";
    }

    @RequiresPermissions("system:config:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Config config) {
        startPage();
        List<Config> configs = configService.selectConfigList(config);
        return getDataTable(configs);
    }

    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    @Log(model = "新增参数", businessType = BusinessType.INSERT)
    @RequiresPermissions("system:config:add")
    @PostMapping("/add")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public Response add(Config config) {
        config.setCreateBy(ShiroUtils.getLoginName());
        return isSuccess(configService.insertConfig(config));
    }

    @GetMapping("/edit/{configId}")
    public String edit(@PathVariable("configId") Long configId, ModelMap map) {
        map.put("config", configService.selectConfigById(configId));
        return prefix + "/edit";
    }

    @Log(model = "更新参数", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:config:edit")
    @PostMapping("/edit")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public Response edit(Config config) {
        config.setUpdateBy(ShiroUtils.getLoginName());
        return isSuccess(configService.updateConfig(config));
    }

    @Log(model = "删除参数", businessType = BusinessType.DELETE)
    @RequiresPermissions("system:config:remove")
    @PostMapping("/remove")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public Response remove(String ids) {
        return isSuccess(configService.deleteConfigByIds(ids));
    }

    @PostMapping("/checkConfigKeyUnique")
    @ResponseBody
    public String checkConfigKeyUnique(Config config) {
        return configService.checkConfigKeyUnique(config);
    }
}
