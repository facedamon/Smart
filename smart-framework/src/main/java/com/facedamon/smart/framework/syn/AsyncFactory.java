package com.facedamon.smart.framework.syn;

import com.facedamon.smart.common.constant.Constants;
import com.facedamon.smart.framework.shiro.session.OnlineSession;
import com.facedamon.smart.framework.util.ApplicationHolder;
import com.facedamon.smart.framework.util.LogUtils;
import com.facedamon.smart.framework.util.ServletUtils;
import com.facedamon.smart.framework.util.ShiroUtils;
import com.facedamon.smart.system.domain.Logininfor;
import com.facedamon.smart.system.domain.OperLog;
import com.facedamon.smart.system.domain.UserOnline;
import com.facedamon.smart.system.service.IOperLogService;
import com.facedamon.smart.system.service.impl.LogininforServiceImpl;
import com.facedamon.smart.system.service.impl.UserOnlineServiceImpl;
import eu.bitwalker.useragentutils.UserAgent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;

/**
 * @Description:    异步工厂
 * @Author:         facedamon
 * @CreateDate:     2018/10/29 13:47
 * @UpdateUser:     facedamon
 * @UpdateDate:     2018/10/29 13:47
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
public enum AsyncFactory {

    /**
     * 实例
     */
    INSTANCE;

    private static final Logger sys_user_logger = LoggerFactory.getLogger("sys-user");

    /**
     * 同步session到数据库
     * @param session
     */
    @Async
    public void asyncSessionToDb(final OnlineSession session){
        UserOnline online = UserOnline.builder()
                .sessionId(String.valueOf(session.getId()))
                .deptName(session.getDeptName())
                .loginName(session.getLoginName())
                .startTimestamp(session.getStartTimestamp())
                .lastAccessTime(session.getLastAccessTime())
                .expireTime(session.getTimeout())
                .ipaddr(session.getHost())
                .browser(session.getBrowser())
                .os(session.getOs())
                .status(session.getStatus())
                .build();
        ApplicationHolder.getBean(UserOnlineServiceImpl.class).saveOnline(online);
    }

    /**
     * 操作日志记录
     * @param operLog
     */
    @Async
    public void recordOper(final OperLog operLog){
        ApplicationHolder.getBean(IOperLogService.class).insertOperlog(operLog);
    }

    /**
     * 记录登录信息
     * @param username
     * @param status
     * @param message
     * @param args
     */
    @Async
    public void recordLogininfor(final String username,final String status,final String message,final Object... args){
        final UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
        final String ip = ShiroUtils.getIp();

        StringBuilder sb = new StringBuilder();
        sb.append(LogUtils.getBlock(ip))
                .append(LogUtils.getBlock(username))
                .append(LogUtils.getBlock(status))
                .append(LogUtils.getBlock(message));
        /**
         * 打印信息到日志
         */
        sys_user_logger.info(sb.toString(),args);

        /**
         * 获取客户端操作系统
         */
        String os = userAgent.getOperatingSystem().getName();

        /**
         * 获取客户端浏览器
         */
        String brower = userAgent.getBrowser().getName();

        Logininfor logininfor = Logininfor.builder()
                .loginName(username)
                .ipaddr(ip)
                .browser(brower)
                .os(os)
                .msg(message)
                .build();

        if (Constants.LOGIN_SUCCESS.getValue().equals(status)
                || Constants.LOGOUT.getValue().equals(status)){
            logininfor.setStatus(Constants.SUCCESS.getValue());
        }else if (Constants.LOGIN_FAIL.getValue().equals(status)){
            logininfor.setStatus(Constants.FAIL.getValue());
        }

        ApplicationHolder.getBean(LogininforServiceImpl.class).insertLogininfor(logininfor);
    }

}
