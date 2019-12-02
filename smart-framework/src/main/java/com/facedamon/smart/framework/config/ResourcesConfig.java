package com.facedamon.smart.framework.config;

import com.facedamon.smart.common.config.SmartConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description: 通用配置
 * @Author: facedamon
 * @CreateDate: 2018/10/29 12:50
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/10/29 12:50
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Configuration
public class ResourcesConfig implements WebMvcConfigurer {

    @Value("${shiro.user.indexUrl}")
    private String indexUrl;

    /**
     * 默认首页设置，当输入域名是可以自动跳转到默认指定的网页
     *
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:" + indexUrl);
    }

    /**
     * 自定义静态资源映射目录
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /**
         * 文件上传路径
         */
        registry.addResourceHandler("/profile/**").addResourceLocations("file:" + SmartConfig.INSTANCE.getProfile());

        /**
         * swagger配置
         */
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
