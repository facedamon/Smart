package com.facedamon.smart.quartz.mapper;

import com.facedamon.smart.quartz.domain.JobLog;

import java.util.List;

/**
 * @Description:    调度任务日志信息mapper
 * @Author:         facedamon
 * @CreateDate:     2018/11/26 15:03
 * @UpdateUser:     facedamon
 * @UpdateDate:     2018/11/26 15:03
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
public interface JobLogMapper {

    /**
     * 查询调度日志
     * @param jobLog
     * @return
     */
    List<JobLog> selectJobLogList(JobLog jobLog);

    /**
     * 根据ID查询任务日志调度信息
     * @param jobLogId
     * @return
     */
    JobLog selectJobLogById(Long jobLogId);

    /**
     * 新增任务日志
     * @param jobLog
     * @return
     */
    int insertJobLog(JobLog jobLog);

    /**
     * 根据ID删除任务日志
     * @param jobId
     * @return
     */
    int deleteJobLogById(Long jobId);

    /**
     * 批量删除调度任务日志
     * @param ids
     * @return
     */
    int deleteJobLogByIds(String[] ids);

    /**
     * 清空任务日志
     */
    void cleanJobLog();
}
