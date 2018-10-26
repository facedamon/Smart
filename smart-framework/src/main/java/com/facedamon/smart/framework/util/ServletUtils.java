package com.facedamon.smart.framework.util;

import com.facedamon.smart.common.utils.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Description: Servlet工具类
 * @Author: facedamon
 * @CreateDate: 2018/8/15 14:29
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/8/15 14:29
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public class ServletUtils {
    private static ServletRequestAttributes getRequestAttributes() {
        return (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    }

    public static HttpServletRequest getRequest() {
        return getRequestAttributes().getRequest();
    }

    public static HttpServletResponse getResponse() {
        return getRequestAttributes().getResponse();
    }

    public static HttpSession getSession() {
        return getRequest().getSession();
    }

    public static String getParameter(String name) {
        return getRequest().getParameter(name);
    }

    public static String getParameter(String name, String defaultValue) {
        String value = getRequest().getParameter(name);
        return StringUtils.isBlank(value) ? defaultValue : value;
    }
}