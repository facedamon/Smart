package com.facedamon.smart.web.controller.system;

import com.facedamon.smart.common.config.SmartConfig;
import com.facedamon.smart.framework.util.ShiroUtils;
import com.facedamon.smart.system.domain.Menu;
import com.facedamon.smart.system.domain.User;
import com.facedamon.smart.system.service.IMenuService;
import com.facedamon.smart.web.core.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @Description: 首页controller
 * @Author: facedamon
 * @CreateDate: 2018/10/30 13:25
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/10/30 13:25
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Controller
public class IndexController extends BaseController {

    @Autowired
    private IMenuService menuService;

    /**
     * 首页
     *
     * @param map
     * @return
     */
    @GetMapping("/index")
    public String index(ModelMap map) {
        User user = ShiroUtils.getUser();
        List<Menu> menuList = menuService.selectMenuByUser(user);
        map.put("menus", menuList);
        map.put("user", user);
        map.put("copyrightYear", SmartConfig.INSTANCE.getCopyrightYear());
        return "index";
    }

    /**
     * 系统介绍
     *
     * @param map
     * @return
     */
    @GetMapping("/system/main")
    public String main(ModelMap map) {
        map.put("version", SmartConfig.INSTANCE.getVersion());
        return "main";
    }
}
