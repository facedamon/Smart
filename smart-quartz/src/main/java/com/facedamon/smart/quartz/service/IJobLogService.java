package com.facedamon.smart.quartz.service;

import com.facedamon.smart.quartz.domain.JobLog;

import java.util.List;

/**
 * @Description: 定时任务调度日志 service
 * @Author: facedamon
 * @CreateDate: 2018/11/26 15:20
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/11/26 15:20
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public interface IJobLogService {

    /**
     * 查询调度器日志
     *
     * @param jobLog
     * @return
     */
    List<JobLog> selectJobLogList(JobLog jobLog);

    /**
     * 根据ID查询调度日志信息
     *
     * @param jobLogId
     * @return
     */
    JobLog selectJobLogById(Long jobLogId);

    /**
     * 新增任务日志
     *
     * @param jobLog
     */
    int addJobLog(JobLog jobLog);

    /**
     * 批量删除调度日志
     *
     * @param ids
     * @return
     */
    int deleteJobLogByIds(String ids);

    /**
     * 删除任务日志
     *
     * @param jobId
     * @return
     */
    int deleteJobLogById(Long jobId);

    /**
     * 清空任务日志
     */
    void cleanJobLog();
}
