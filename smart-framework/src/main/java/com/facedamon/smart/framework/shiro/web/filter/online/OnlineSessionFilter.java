package com.facedamon.smart.framework.shiro.web.filter.online;

import com.facedamon.smart.common.constant.ShiroConstants;
import com.facedamon.smart.common.enums.OnlineStatus;
import com.facedamon.smart.framework.shiro.session.OnlineSession;
import com.facedamon.smart.framework.shiro.session.OnlineSessionDAO;
import com.facedamon.smart.framework.util.ShiroUtils;
import com.facedamon.smart.system.domain.User;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * @Description: 自定义访问控制 实现模板方法
 * @Author: facedamon
 * @CreateDate: 2018/10/6 下午5:26
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/10/6 下午5:26
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public class OnlineSessionFilter extends AccessControlFilter {

    @Value("${shiro.user.loginUrl}")
    private String loginUrl;

    @Autowired
    private OnlineSessionDAO onlineSessionDAO;

    /**
     * 是否允许访问
     *
     * @param request
     * @param response
     * @param mappedValue
     * @return
     * @throws Exception
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        Subject subject = getSubject(request, response);
        if (null == subject || subject.getSession() == null) {
            return true;
        }
        Session session = onlineSessionDAO.readSession(subject.getSession().getId());
        if (null != session && session instanceof OnlineSession) {
            OnlineSession onlineSession = (OnlineSession) session;
            request.setAttribute(ShiroConstants.ONLINE_SESSION.getValue(), onlineSession);
            boolean isGust = onlineSession.getUserId() == null || onlineSession.getUserId() == 0L;

            if (isGust) {
                User user = ShiroUtils.getUser();
                if (null != user) {
                    onlineSession.setUserId(user.getUserId());
                    onlineSession.setLoginName(user.getLoginName());

                    onlineSession.setDeptName(user.getDept().getDeptName());

                    onlineSession.markAttributeChanged();
                }
            }
            if (OnlineStatus.OFF_LINE.compareTo(onlineSession.getStatus()) == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 表示当访问拒绝时是否已经处理了；
     * 如果返回true表示需要继续处理；
     * 如果返回false表示该拦截器实例已经处理了，将直接返回即可。
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = getSubject(request, response);
        if (null != subject) {
            subject.logout();
        }
        saveRequestAndRedirectToLogin(request, response);
        return true;
    }

    /**
     * 跳转到登录页
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @Override
    protected void redirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
        WebUtils.issueRedirect(request, response, loginUrl);
    }
}