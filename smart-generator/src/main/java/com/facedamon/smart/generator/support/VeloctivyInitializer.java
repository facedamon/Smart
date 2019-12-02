package com.facedamon.smart.generator.support;

import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.app.Velocity;

import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * @Description: VelocityEngine Factory
 * @Author: facedamon
 * @CreateDate: 2018/11/21 12:56
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/11/21 12:56
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Slf4j
public class VeloctivyInitializer {

    public static void initVelocity() {
        Properties properties = new Properties();

        try {
            /**
             * 注册vm加载器
             */
            properties.setProperty("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");

            /**
             * 设置字符集
             */
            properties.setProperty(Velocity.ENCODING_DEFAULT, StandardCharsets.UTF_8.displayName());
            properties.setProperty(Velocity.OUTPUT_ENCODING, StandardCharsets.UTF_8.displayName());

            Velocity.init(properties);
        } catch (Exception e) {
            log.error("加载velocity模板失败", e.getMessage());
            throw new RuntimeException(e);
        }

    }
}
