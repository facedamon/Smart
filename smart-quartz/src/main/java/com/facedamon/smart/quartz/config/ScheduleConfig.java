package com.facedamon.smart.quartz.config;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * @Description: 定时配置
 * @Author: facedamon
 * @CreateDate: 2018/11/26 14:03
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/11/26 14:03
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Configuration
public class ScheduleConfig {

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(DataSource dataSource) throws IOException {
        SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setQuartzProperties(quartz());
        factoryBean.setSchedulerName("SmartScheduler");
        factoryBean.setStartupDelay(1);
        factoryBean.setApplicationContextSchedulerContextKey("applicationContextKey");
        factoryBean.setOverwriteExistingJobs(true);
        factoryBean.setAutoStartup(true);
        return factoryBean;
    }

    @Bean
    public Properties quartz() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }
}
