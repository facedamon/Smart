package com.facedamon.smart.core.factory;

import com.facedamon.smart.common.constant.Constants;
import com.facedamon.smart.common.utils.AddressUtils;
import com.facedamon.smart.common.utils.ApplicationHolder;
import com.facedamon.smart.common.utils.LogUtils;
import com.facedamon.smart.common.utils.ServletUtils;
import com.facedamon.smart.common.utils.security.ShiroUtils;
import com.facedamon.smart.project.monitor.logininfor.domain.Logininfor;
import com.facedamon.smart.project.monitor.logininfor.service.LogininforServiceImpl;
import com.facedamon.smart.project.monitor.online.domain.OnlineSession;
import com.facedamon.smart.project.monitor.online.domain.UserOnline;
import com.facedamon.smart.project.monitor.online.service.IUserOnlineService;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;

/**
 * @Description: 异步工厂 产生任务
 * @Author: facedamon
 * @CreateDate: 2018/10/15 下午7:19
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/10/15 下午7:19
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public class AsyncFactory {

    private static final Logger log = LoggerFactory.getLogger("sys-user");

    private final UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
    private final String ip = ShiroUtils.getIp();


    /**
     * 同步session到数据库
     *
     * @param session
     */
    @Async
    public void syncSessionToDb(final OnlineSession session) {

        UserOnline online = UserOnline.builder().sessionId(String.valueOf(session.getId()))
                .deptName(session.getDeptName())
                .loginName(session.getLoginName())
                .startTimestamp(session.getStartTimestamp())
                .lastAccessTime(session.getLastAccessTime())
                .expireTime(session.getTimeout())
                .ipaddr(session.getHost())
                .loginLocation(AddressUtils.getRealAddressByIP(session.getHost()))
                .browser(session.getBrowser())
                .os(session.getOs())
                .status(session.getStatus())
                .session(session)
                .build();


        ApplicationHolder.getBean(IUserOnlineService.class).saveOnline(online);
    }

    /**
     * 记录登陆信息
     *
     * @param username
     * @param status
     * @param message
     * @param args
     */
    @Async
    public void recordLogininfor(final String username, final String status, final String message, final Object... args) {

        StringBuilder s = new StringBuilder();
        s.append(LogUtils.getBlock(ip));
        s.append(AddressUtils.getRealAddressByIP(ip));
        s.append(LogUtils.getBlock(username));
        s.append(LogUtils.getBlock(status));
        s.append(LogUtils.getBlock(message));
        // 打印信息到日志
        log.info(s.toString(), args);
        // 获取客户端操作系统
        String os = userAgent.getOperatingSystem().getName();
        // 获取客户端浏览器
        String browser = userAgent.getBrowser().getName();
        // 封装对象

        Logininfor logininfor = Logininfor.builder().loginName(username)
                .ipaddr(ip)
                .loginLocation(AddressUtils.getRealAddressByIP(ip))
                .browser(browser)
                .os(os)
                .msg(message)
                .build();


        // 日志状态
        if (Constants.LOGIN_SUCCESS.equals(status) || Constants.LOGOUT.equals(status)) {
            logininfor.setStatus(Constants.SUCCESS.getValue());
        } else if (Constants.LOGIN_FAIL.equals(status)) {
            logininfor.setStatus(Constants.FAIL.getValue());
        }
        // 插入数据
        ApplicationHolder.getBean(LogininforServiceImpl.class).insertLogininfor(logininfor);
    }
}
