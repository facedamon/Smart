package com.facedamon.smart.web.controller.monitor;

import com.facedamon.smart.common.annotation.Log;
import com.facedamon.smart.common.base.Response;
import com.facedamon.smart.common.enums.BusinessType;
import com.facedamon.smart.common.enums.OnlineStatus;
import com.facedamon.smart.framework.shiro.session.OnlineSession;
import com.facedamon.smart.framework.shiro.session.OnlineSessionDAO;
import com.facedamon.smart.framework.util.ShiroUtils;
import com.facedamon.smart.framework.web.page.TableDataInfo;
import com.facedamon.smart.system.domain.UserOnline;
import com.facedamon.smart.system.service.IUserOnlineService;
import com.facedamon.smart.web.core.base.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.UnknownSessionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.facedamon.smart.common.base.Response.success;

/**
 * @Description: 在线用户
 * @Author: facedamon
 * @CreateDate: 2018/11/23 17:38
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/11/23 17:38
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Controller
@RequestMapping("/monitor/online")
public class UserOnlineController extends BaseController {

    private String prefix = "monitor/online";

    @Autowired
    private IUserOnlineService userOnlineService;

    /**
     * session触发器
     */
    @Autowired
    private OnlineSessionDAO onlineSessionDAO;

    @RequiresPermissions("monitor:online:view")
    @GetMapping
    public String online() {
        return prefix + "/online";
    }

    @RequiresPermissions("monitor:online:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(UserOnline userOnline) {
        startPage();
        List<UserOnline> list = userOnlineService.selectUserOnlineList(userOnline);
        return getDataTable(list);
    }

    @Log(model = "强退用户", businessType = BusinessType.FORCE)
    @RequiresPermissions("monitor:online:forceLogout")
    @PostMapping("/forceLogout")
    @ResponseBody
    public Response forceLogout(String sessionId) {
        UserOnline online = userOnlineService.selectOnlineById(sessionId);
        if (sessionId.equals(ShiroUtils.getSessionId())) {
            return Response.error("当前登录用户无法强退,如需退出，请点击右上角退出按钮");
        }
        if (null == online) {
            return Response.error("该用户已下线");
        }
        OnlineSession onlineSession = null;
        try {
            onlineSession = (OnlineSession) onlineSessionDAO.readSession(sessionId);
        } catch (UnknownSessionException e) {
            userOnlineService.deleteOnlineById(online.getSessionId());
            return success();
        }

        if (null == onlineSession) {
            return Response.error("该用户已下线");
        }
        onlineSession.setStatus(OnlineStatus.OFF_LINE);
        online.setStatus(OnlineStatus.OFF_LINE);
        userOnlineService.saveOnline(online);

        return success();
    }

    @Log(model = "批量强退用户", businessType = BusinessType.FORCE)
    @RequiresPermissions("monitor:online:forceLogout")
    @PostMapping("/batchForceLogout")
    @ResponseBody
    public Response batchForceLogout(@RequestParam("ids[]") String[] ids) {
        for (String sessionId : ids) {
            UserOnline online = userOnlineService.selectOnlineById(sessionId);
            if (null == online) {
                return Response.error("该用户已下线");
            }
            OnlineSession onlineSession = null;
            try {
                onlineSession = (OnlineSession) onlineSessionDAO.readSession(sessionId);
            } catch (UnknownSessionException e) {
                userOnlineService.deleteOnlineById(online.getSessionId());
                return success();
            }
            if (null == onlineSession) {
                return Response.error("该用户已下线");
            }
            if (sessionId.equals(ShiroUtils.getSessionId())) {
                return Response.error("当前登录用户无法强退");
            }
            onlineSession.setStatus(OnlineStatus.OFF_LINE);
            online.setStatus(OnlineStatus.OFF_LINE);
            userOnlineService.saveOnline(online);
        }
        return success();
    }
}
