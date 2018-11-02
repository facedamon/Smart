package com.facedamon.smart.framework.config;

import com.facedamon.smart.common.utils.StringUtils;
import com.facedamon.smart.common.xss.XssFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.DispatcherType;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:    xss config
 * @Author:         facedamon
 * @CreateDate:     2018/10/29 13:02
 * @UpdateUser:     facedamon
 * @UpdateDate:     2018/10/29 13:02
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
@Configuration
public class XssConfig {

    @Value("${xss.enabled}")
    private String enabled;

    @Value("${xss.excludes}")
    private String excludes;

    @Value("${xss.urlPatterns}")
    private String urlPatterns;

    /**
     * springboot filter register
     * @return
     */
    @Bean
    public FilterRegistrationBean xssFilterRegistration(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setDispatcherTypes(DispatcherType.REQUEST);
        registrationBean.setFilter(new XssFilter());
        registrationBean.addUrlPatterns(StringUtils.split(urlPatterns,","));
        registrationBean.setName("xssFilter");
        registrationBean.setOrder(Integer.MAX_VALUE);
        Map<String,String> initParameters = new HashMap<>(2);
        initParameters.put("excludes",excludes);
        initParameters.put("enabled",enabled);
        registrationBean.setInitParameters(initParameters);
        return registrationBean;
    }
}
