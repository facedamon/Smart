package com.facedamon.smart.quartz.support;

import com.facedamon.smart.common.constant.ScheduleConstants;
import com.facedamon.smart.common.exception.job.TaskException;
import com.facedamon.smart.quartz.domain.Job;
import javafx.concurrent.Task;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;

/**
 * @Description:    定时任务support
 * @Author:         facedamon
 * @CreateDate:     2018/11/26 15:37
 * @UpdateUser:     facedamon
 * @UpdateDate:     2018/11/26 15:37
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
@Slf4j
public class ScheduleUtils {

    /**
     * 获取触发器key
     * @param jobId
     * @return
     */
    public static TriggerKey getTriggerKey(Long jobId){
        return TriggerKey.triggerKey(ScheduleConstants.TASK_CLASS_NAME + jobId);
    }

    /**
     * 获取jobkey
     * @param jobId
     * @return
     */
    public static JobKey getJobKey(Long jobId){
        return JobKey.jobKey(ScheduleConstants.TASK_CLASS_NAME + jobId);
    }

    /**
     * 获取表达式触发器
     * @param scheduler
     * @param jobId
     * @return
     */
    public static CronTrigger getCrontrigger(Scheduler scheduler,Long jobId){
        try {
            return (CronTrigger) scheduler.getTrigger(getTriggerKey(jobId));
        } catch (SchedulerException e) {
            log.error("getCrontrigger 异常:",e);
        }
        return null;
    }

    /**
     * 创建定时任务
     * @param scheduler
     * @param job
     */
    public static void createScheduleJob(Scheduler scheduler,Job job){
        try {
            /**
             * 构建job
             */
            JobDetail jobDetail = JobBuilder.newJob(ScheduleJob.class)
                    .withIdentity(getJobKey(job.getJobId()))
                    .build();

            /**
             * 表达式调度构建器
             */
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
            /**
             * 表达式策略构建
             */
            cronScheduleBuilder = handlerCronScheduleMisfirePolicy(cronScheduleBuilder,job);

            /**
             * 按新的cronExpression表达式构建一个新的trigger
             */
            CronTrigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity(getTriggerKey(job.getJobId()))
                    .withSchedule(cronScheduleBuilder)
                    .build();
            /**
             * 放入参数，运行时的方法能够获取
             */
            jobDetail.getJobDataMap().put(ScheduleConstants.TASK_PROPERTIES,job);
            scheduler.scheduleJob(jobDetail,trigger);

            /**
             * 暂停
             */
            if (job.getStatus().equals(ScheduleConstants.Status.PAUSE.getValue())){
                pauseJob(scheduler,job.getJobId());
            }
        } catch (SchedulerException e){
            log.error("createScheduleJob 异常:",e);
        } catch (TaskException e){
            log.error("createScheduleJob 异常:",e);
        }

    }

    /**
     * 更新定时任务
     * @param scheduler
     * @param job
     */
    public static void updateScheduleJob(Scheduler scheduler,Job job){
        try {
            TriggerKey triggerKey = getTriggerKey(job.getJobId());

            /**
             * 表达式调度构建器
             */
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
            /**
             * 表达式调度策略
             */
            cronScheduleBuilder = handlerCronScheduleMisfirePolicy(cronScheduleBuilder,job);

            /**
             * 获取待更新的trigger
             */
            CronTrigger trigger = getCrontrigger(scheduler,job.getJobId());

            /**
             * 按照新的cronExpression表达式重新构建trigger
             */
            trigger = trigger.getTriggerBuilder()
                    .withIdentity(triggerKey)
                    .withSchedule(cronScheduleBuilder)
                    .build();

            /**
             * 放入参数
             */
            trigger.getJobDataMap().put(ScheduleConstants.TASK_PROPERTIES,job);

            scheduler.rescheduleJob(triggerKey,trigger);

            if (job.getStatus().equals(ScheduleConstants.Status.PAUSE.getValue())){
                pauseJob(scheduler,job.getJobId());
            }

        } catch (SchedulerException e){
            log.error("updateScheduleJob 异常:",e);
        } catch (TaskException e){
            log.error("updateScheduleJob 异常:",e);
        }
    }

    /**
     * 暂停任务
     * @param scheduler
     * @param jobId
     */
    public static void pauseJob(Scheduler scheduler,Long jobId){
        try {
            scheduler.pauseJob(getJobKey(jobId));
        } catch (SchedulerException e) {
            log.error("暂停任务异常： ",e);
        }
    }

    /**
     * 恢复任务
     * @param scheduler
     * @param jobId
     */
    public static void resumeJob(Scheduler scheduler,Long jobId){
        try {
            scheduler.resumeJob(getJobKey(jobId));
        } catch (SchedulerException e) {
            log.error("恢复任务异常: ",e);
        }
    }

    /**
     * 删除任务
     * @param scheduler
     * @param jobId
     */
    public static void deleteScheduleJob(Scheduler scheduler,Long jobId){
        try {
            scheduler.deleteJob(getJobKey(jobId));
        } catch (SchedulerException e) {
            log.error("删除任务异常: ",e);
        }
    }

    /**
     * 运行
     * @param scheduler
     * @param job
     */
    public static int run(Scheduler scheduler,Job job){
        JobDataMap dataMap = new JobDataMap();
        dataMap.put(ScheduleConstants.TASK_PROPERTIES,job);
        int rows = 0;

        try {
            scheduler.triggerJob(getJobKey(job.getJobId()),dataMap);
            rows = 1;
        } catch (SchedulerException e) {
            log.error("run 异常",e);
        }
        return rows;
    }

    /**
     * 超时策略
     * @param cronScheduleBuilder
     * @param job
     * @return
     */
    public static CronScheduleBuilder handlerCronScheduleMisfirePolicy(CronScheduleBuilder cronScheduleBuilder,Job job)
            throws TaskException {
        switch (job.getMisfirePolicy()){
            case ScheduleConstants.MISFIRE_DEFAULT:
                return cronScheduleBuilder;
            case ScheduleConstants.MISFIRE_IGNORE_MISFIRES:
                return cronScheduleBuilder.withMisfireHandlingInstructionIgnoreMisfires();
            case ScheduleConstants.MISFIRE_FIRE_AND_PROCEED:
                return cronScheduleBuilder.withMisfireHandlingInstructionFireAndProceed();
            case ScheduleConstants.MISFIRE_DO_NOTHING:
                return cronScheduleBuilder.withMisfireHandlingInstructionDoNothing();
            default:
                throw new TaskException("the task misfire policy '" + job.getMisfirePolicy() + "' cannot be used in cron schedule tasks",TaskException.Code.CONFIG_ERROR);
        }
    }
}
