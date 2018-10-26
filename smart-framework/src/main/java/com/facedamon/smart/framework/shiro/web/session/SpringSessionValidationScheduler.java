package com.facedamon.smart.framework.shiro.web.session;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.session.mgt.SessionValidationScheduler;
import org.apache.shiro.session.mgt.ValidatingSessionManager;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 自定义session调度器 定时检查session
 * @Author: facedamon
 * @CreateDate: 2018/10/7 上午11:18
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/10/7 上午11:18
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Slf4j
public class SpringSessionValidationScheduler implements SessionValidationScheduler {

    /**
     * 默认检查频率
     */
    public static final long DEFAULT_SESSION_VALIDATION_INTERVAL = DefaultSessionManager.DEFAULT_SESSION_VALIDATION_INTERVAL;

    //private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    /**
     * 处理超时的挂起请求，连接断开的重连
     */
    private final ScheduledExecutorService executorService =
            new ScheduledThreadPoolExecutor(1, new BasicThreadFactory.Builder().namingPattern("example-sheduled-pool-%d").daemon(true).build());

    private volatile boolean enabled = false;


    private ValidatingSessionManager validatingSessionManager;
    private long sessionValidationInterval = DEFAULT_SESSION_VALIDATION_INTERVAL;

    public SpringSessionValidationScheduler(ValidatingSessionManager validatingSessionManager) {
        this.validatingSessionManager = validatingSessionManager;
    }

    public SpringSessionValidationScheduler() {
    }

    public ValidatingSessionManager getValidatingSessionManager() {
        return validatingSessionManager;
    }

    public void setSessionValidationInterval(long sessionValidationInterval) {
        this.sessionValidationInterval = sessionValidationInterval;
    }

    public void setValidatingSessionManager(ValidatingSessionManager sessionManager){
        this.validatingSessionManager = sessionManager;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void enableSessionValidation() {
        enabled = true;

        if (log.isDebugEnabled()) {
            log.debug("Scheduling session validation job using Spring Scheduler with "
                    + "session validation interval of [" + sessionValidationInterval + "]ms...");
        }

        try {
            executorService.scheduleAtFixedRate(() -> {
                        if (enabled) {
                            validatingSessionManager.validateSessions();
                        }
                    },
                    1000, sessionValidationInterval, TimeUnit.MILLISECONDS);
            enabled = true;

            if (log.isDebugEnabled()) {
                log.debug("Session validation job successfully scheduled with Spring Scheduler.");
            }
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("Error starting the Spring Scheduler session validation job.  Session validation may not occur.", e);
            }
        }
    }

    @Override
    public void disableSessionValidation() {
        if (log.isDebugEnabled()) {
            log.debug("Stopping Spring Scheduler session validation job...");
        }

        enabled = false;
    }
}