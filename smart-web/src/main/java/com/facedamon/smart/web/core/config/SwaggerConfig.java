package com.facedamon.smart.web.core.config;

import com.facedamon.smart.common.config.SmartConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Description: Swagger2 Config
 * @Author: facedamon
 * @CreateDate: 2018/11/26 12:06
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/11/26 12:06
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * 创建API
     *
     * @return
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.facedamon.smart.web.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 添加摘要信息
     *
     * @return
     */
    @Bean
    protected ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("标题: Smart接口文档")
                .description("描述: 用于开发及维护产品,具体包括核心模块,WEB模块,常规模块，系统模块,定时器模块,逆向工程模块")
                .contact(new Contact(SmartConfig.INSTANCE.getName(), null, null))
                .version("版本号: " + SmartConfig.INSTANCE.getVersion())
                .build();
    }
}
