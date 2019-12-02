package com.facedamon.smart.framework.shiro.web;

import com.facedamon.smart.common.constant.Constants;
import com.facedamon.smart.common.utils.StringUtils;
import com.facedamon.smart.framework.syn.AsyncFactory;
import com.facedamon.smart.framework.util.MessageUtils;
import com.facedamon.smart.framework.util.ShiroUtils;
import com.facedamon.smart.system.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @Description: 退出过滤连
 * @Author: facedamon
 * @CreateDate: 2018/10/26 15:43
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/10/26 15:43
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Slf4j
public class LogoutFilter extends org.apache.shiro.web.filter.authc.LogoutFilter {

    /**
     * 退出后重定向的地址
     */
    private String loginUrl;

    public String getLoginUrl() {
        return loginUrl;
    }

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
                    AsyncFactory.INSTANCE.recordLogininfor(loginName, Constants.LOGOUT.getValue(), MessageUtils.message("user.logout.success"));
                }
                // 退出登录
                subject.logout();
            } catch (SessionException ise) {
                log.error("logout fail.", ise);
            }
            issueRedirect(request, response, redirectUrl);
        } catch (Exception e) {
            log.error("Encountered session exception during logout.  This can generally safely be ignored.", e);
        }
        return false;
    }

    /**
     * 退出跳转URL
     */
    @Override
    protected String getRedirectUrl(ServletRequest request, ServletResponse response, Subject subject) {
        String url = getLoginUrl();
        if (StringUtils.isNotEmpty(url)) {
            return url;
        }
        return super.getRedirectUrl(request, response, subject);
    }
}