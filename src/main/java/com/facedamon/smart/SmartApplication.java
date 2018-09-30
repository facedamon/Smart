package com.facedamon.smart;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @Description:    工程启动入口
 * @Author:         facedamon
 * @CreateDate:     2018/9/30 下午9:11
 * @UpdateUser:     facedamon
 * @UpdateDate:     2018/9/30 下午9:11
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@MapperScan("com.facedamon.smart.project.*.*.mapper")
public class SmartApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartApplication.class, args);
    }
}
