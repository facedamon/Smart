package com.facedamon.smart.common.xss;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * @Description:    xss 处理excludes之外的链接
 * @Author:         facedamon
 * @CreateDate:     2018/10/29 13:36
 * @UpdateUser:     facedamon
 * @UpdateDate:     2018/10/29 13:36
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

    public XssHttpServletRequestWrapper(HttpServletRequest req) {
        super(req);
    }

    /**
     * key:value[]
     * @param name
     * @return
     */
    @Override
    public String[] getParameterValues(String name) {
        String[] values = super.getParameterValues(name);
        if (null != values){
            int len = values.length;
            String[] escapseValues = new String[len];
            for (int i = 0; i < len; i++){
                /**
                 * 白名单过滤参数
                 */
                escapseValues[i] = Jsoup.clean(values[i],Whitelist.relaxed()).trim();
            }
        }
        return values;
    }
}
