package com.facedamon.smart.core.shiro.session;

import com.facedamon.smart.common.utils.IpUtils;
import com.facedamon.smart.common.utils.ServletUtils;
import com.facedamon.smart.project.monitor.online.domain.OnlineSession;
import com.facedamon.smart.project.monitor.online.domain.UserOnline;
import eu.bitwalker.useragentutils.UserAgent;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.session.mgt.SessionFactory;
import org.apache.shiro.web.session.mgt.WebSessionContext;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description:    自定义session factory 创建session
 * @Author:         facedamon
 * @CreateDate:     2018/10/6 下午4:50
 * @UpdateUser:     facedamon
 * @UpdateDate:     2018/10/6 下午4:50
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
@Component
public class OnlineSessionFactory implements SessionFactory {

    @Override
    public Session createSession(SessionContext initData) {
        OnlineSession session = new OnlineSession();
        if (null != initData && initData instanceof WebSessionContext){
            WebSessionContext sessionContext = (WebSessionContext) initData;
            HttpServletRequest request = (HttpServletRequest) sessionContext.getServletRequest();
            if (null != request){
                UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
                /**
                 * 获取客户端操作系统
                 */
                String os = userAgent.getOperatingSystem().getName();

                /**
                 * 获取客户端浏览器类型
                 */
                String browser = userAgent.getBrowser().getName();
                session.setHost(IpUtils.getIpAddr(request));
                session.setOs(os);
                session.setBrowser(browser);
            }
        }
        return session;
    }

    public Session createSession(UserOnline userOnline){
        OnlineSession session = userOnline.getSession();
        if (null != session && session.getId() == null){
            session.setId(userOnline.getSessionId());
        }
        return session;
    }
}
