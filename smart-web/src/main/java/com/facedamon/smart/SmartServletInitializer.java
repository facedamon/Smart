package com.facedamon.smart;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @Description:    web 容器部署
 * @Author:         facedamon
 * @CreateDate:     2018/11/22 14:04
 * @UpdateUser:     facedamon
 * @UpdateDate:     2018/11/22 14:04
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
public class SmartServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SmartApplication.class);
    }
}
