package com.facedamon.smart.common.xss;

import com.facedamon.smart.common.utils.StringUtils;
import org.apache.commons.collections4.CollectionUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description: 防止xss攻击的过滤器
 * @Author: facedamon
 * @CreateDate: 2018/10/29 13:09
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/10/29 13:09
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public class XssFilter implements Filter {

    /**
     * 排除链接
     */
    private List<String> excludes = new ArrayList<>();

    /**
     * xss 过滤器开关
     */
    private boolean enabled = false;

    /**
     * 设置变量
     *
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String excludes = filterConfig.getInitParameter("excludes");
        String enabled = filterConfig.getInitParameter("enabled");

        if (StringUtils.isNotBlank(excludes)) {
            String[] url = excludes.split(",");
            for (int i = 0; url != null && i < url.length; i++) {
                this.excludes.add(url[i]);
            }
        }

        if (StringUtils.isNotBlank(enabled)) {
            this.enabled = Boolean.valueOf(enabled);
        }

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        /**
         * 跳过excludes
         */
        if (handleExcludeURL(req, resp)) {
            chain.doFilter(request, response);
            return;
        }
        /**
         * 处理excludesh之外的链接
         */
        XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper(req);
        chain.doFilter(xssRequest, response);
    }

    protected boolean handleExcludeURL(HttpServletRequest req, HttpServletResponse resp) {
        if (!enabled) {
            return true;
        }
        if (CollectionUtils.isEmpty(excludes)) {
            return false;
        }
        String url = req.getServletPath();
        for (String pattern : excludes) {
            /**
             * 以pattern开头的链接
             */
            Pattern p = Pattern.compile("^" + pattern);
            Matcher m = p.matcher(url);
            if (m.find()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void destroy() {

    }
}
