package com.facedamon.smart.framework.syn;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @Description: 自定义异步线程池
 * @Author: facedamon
 * @CreateDate: 2018/10/29 13:54
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/10/29 13:54
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Configuration
public class AsyncConfig {

    @Value("${async.maxPoolSize}")
    private Integer maxPoolSize;

    @Value("${async.queueCapacity}")
    private Integer queueCapacity;

    @Value("${async.keepAliveSeconds}")
    private Integer keepAliveSeconds;

    @Bean
    public AsyncTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setThreadNamePrefix("Anno-Executor");
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setKeepAliveSeconds(keepAliveSeconds);
        return executor;
    }
}
