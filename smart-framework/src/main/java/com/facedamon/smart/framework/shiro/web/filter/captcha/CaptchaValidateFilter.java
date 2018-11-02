package com.facedamon.smart.framework.shiro.web.filter.captcha;


import com.facedamon.smart.common.constant.ShiroConstants;
import com.facedamon.smart.framework.util.ShiroUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * @Description:    验证码过滤器
 * @Author:         facedamon
 * @CreateDate:     2018/10/29 11:37
 * @UpdateUser:     facedamon
 * @UpdateDate:     2018/10/29 11:37
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
public class CaptchaValidateFilter extends AccessControlFilter {

    /**
     * 是否开启验证码
     */
    private boolean captchaEnabled = true;

    /**
     * 验证码类型
     */
    private String captchaType = "math";

    public void setCaptchaEnabled(boolean captchaEnabled) {
        this.captchaEnabled = captchaEnabled;
    }

    public void setCaptchaType(String captchaType) {
        this.captchaType = captchaType;
    }

    /**
     * 前置控制器
     * @param request
     * @param response
     * @param mappedValue
     * @return
     * @throws Exception
     */
    @Override
    public boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        request.setAttribute(ShiroConstants.CURRENT_ENABLED.getValue(),captchaEnabled);
        request.setAttribute(ShiroConstants.CURRENT_TYPE.getValue(),captchaType);
        return super.onPreHandle(request, response, mappedValue);
    }

    /**
     * 核心控制器
     * @param servletRequest
     * @param servletResponse
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        /**
         * 验证码禁用，或不是表单提交模式，允许访问
         */
        if (!captchaEnabled || !"post".equals(httpServletRequest.getMethod().toLowerCase())){
            return true;
        }
        return validateResponse(httpServletRequest,httpServletRequest.getParameter(ShiroConstants.CURRENT_VALIDATECODE.getValue()));
    }

    /**
     *  验证码匹配
     * @param httpServletRequest
     * @param validateCode
     * @return
     */
    protected boolean validateResponse(HttpServletRequest httpServletRequest, String validateCode) {
        Object obj = ShiroUtils.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
        String code = String.valueOf(null != obj ? obj : "");
        /**
         * 验证码不匹配
         */
        if (StringUtils.isBlank(validateCode) || !validateCode.equalsIgnoreCase(code)){
            return false;
        }
        return true;
    }

    /**
     * 拒绝策略
     * @param servletRequest
     * @param servletResponse
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        servletRequest.setAttribute(ShiroConstants.CURRENT_CAPTCHA.getValue(), ShiroConstants.CAPTCHA_ERROR.getValue());
        return true;
    }
}
