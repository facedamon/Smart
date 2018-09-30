package com.facedamon.smart.core.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.facedamon.smart.core.aspj.constants.DataSourceName;
import com.facedamon.smart.core.datasource.DynamicDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
* @Description:    Druid多数据源配置，核心思想通过extends AbstractRoutingDataSource设置数据源
 *                 触发时机：自定义注解，AOP拦截
* @Author:         facedamon
* @CreateDate:     2018/8/14 16:14
* @UpdateUser:     facedamon
* @UpdateDate:     2018/8/14 16:14
* @UpdateRemark:   修改内容
* @Version:        1.0
*/
@Configuration
public class DruidConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.druid.master")
    public DataSource masterDataSource(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.druid.slave")
    @ConditionalOnProperty(prefix = "spring.datasource.druid.slave", name = "enabled",havingValue = "true")
    public DataSource slaveDataSource(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean("dynamicDataSource")
    @Primary
    public DynamicDataSource dataSource(DataSource masterDataSource,DataSource slaveDataSource){
        Map<Object,Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceName.MASTER,masterDataSource);
        targetDataSources.put(DataSourceName.SLAVE,slaveDataSource);
        return new DynamicDataSource(masterDataSource,targetDataSources);
    }
}
