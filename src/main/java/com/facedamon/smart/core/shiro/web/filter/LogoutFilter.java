package com.facedamon.smart.core.shiro.web.filter;

import com.facedamon.smart.common.utils.StringUtils;
import com.facedamon.smart.common.utils.security.ShiroUtils;
import com.facedamon.smart.project.system.user.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.subject.Subject;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @Description: 增加日志记录
 * @Author: facedamon
 * @CreateDate: 2018/10/6 下午6:17
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/10/6 下午6:17
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Slf4j
public class LogoutFilter extends org.apache.shiro.web.filter.authc.LogoutFilter {

    private String loginUrl;

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        try {
            Subject subject = getSubject(request, response);
            String redirectUrl = getRedirectUrl(request, response, subject);

            try {
                User user = ShiroUtils.getUser();
                if (null != user) {
                    String loginName = user.getLoginName();
                    //TODO 记录推出日志
                }
                subject.logout();
            } catch (org.apache.shiro.session.SessionException e) {
                log.error("logout failed.", e);
            }
            issueRedirect(request, response, redirectUrl);
        } catch (Exception e) {
            log.error("Encountered session exception during logout.  This can generally safely be ignored.", e);
        }
        return false;
    }

    @Override
    protected String getRedirectUrl(ServletRequest request, ServletResponse response, Subject subject) {
        if (StringUtils.isNotBlank(loginUrl)) {
            return loginUrl;
        }
        return super.getRedirectUrl(request, response, subject);
    }
}