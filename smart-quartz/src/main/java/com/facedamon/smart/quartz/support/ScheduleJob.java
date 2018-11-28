package com.facedamon.smart.quartz.support;

import com.facedamon.smart.common.constant.Constants;
import com.facedamon.smart.common.constant.ScheduleConstants;
import com.facedamon.smart.common.utils.DateUtils;
import com.facedamon.smart.quartz.domain.Job;
import com.facedamon.smart.quartz.domain.JobLog;
import com.facedamon.smart.quartz.service.IJobLogService;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.concurrent.*;

/**
 * @Description:    定时调度核心任务处理器
 * @Author:         facedamon
 * @CreateDate:     2018/11/26 15:49
 * @UpdateUser:     facedamon
 * @UpdateDate:     2018/11/26 15:49
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
public class ScheduleJob extends QuartzJobBean {

    private static final Logger log = LoggerFactory.getLogger("quartz");

    private final ScheduledExecutorService executorService =
            new ScheduledThreadPoolExecutor(1, new BasicThreadFactory.Builder().namingPattern("example-sheduled-pool-%d").daemon(true).build());

    private static final IJobLogService jobLogService = (IJobLogService) ApplicationHolders.getBean("jobLogServiceImpl");

    /**
     * 核心任务执行器
     * @param context job执行器上下文
     * @throws JobExecutionException
     */
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        Job job = new Job();
        BeanUtils.copyProperties(context.getMergedJobDataMap().get(ScheduleConstants.TASK_PROPERTIES),job);

        JobLog jobLog = JobLog.builder().jobName(job.getJobName())
                .jobGroup(job.getJobGroup())
                .methodName(job.getMethodName())
                .methodParams(job.getMethodParams())
                .build();
        jobLog.setCreateTime(DateUtils.getNowDate());

        Long startTime = System.currentTimeMillis();

        try {
            log.info("任务开始执行 - 名称:{} 方法:{}",job.getJobName(),job.getMethodName());
            ScheduleRunnable task = new ScheduleRunnable(job.getJobName(),job.getMethodName(),job.getMethodParams());
            /**
             * 异步执行
             */
            Future<?> future = executorService.submit(task);
            future.get();

            long times = System.currentTimeMillis() - startTime;

            jobLog.setStatus(Constants.SUCCESS.getValue());
            jobLog.setJobMessage(job.getJobName() + "总耗时:" + times + "毫秒");

            log.info("任务执行结束 - 名称:{} 耗时:{} 毫秒",job.getJobName(),times);
        }catch(Exception e){
            log.error("任务执行失败 - 名称：{} 方法：{}", job.getJobName(), job.getMethodName());
            log.error("任务执行异常  - ：", e);
            long times = System.currentTimeMillis() - startTime;
            jobLog.setJobMessage(job.getJobName() + " 总共耗时：" + times + "毫秒");
            jobLog.setStatus(Constants.FAIL.getValue());
            jobLog.setExceptionInfo(e.toString());
        }finally {
            jobLogService.addJobLog(jobLog);
        }
    }
}
