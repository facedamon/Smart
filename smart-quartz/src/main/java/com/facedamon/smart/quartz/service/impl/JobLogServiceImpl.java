package com.facedamon.smart.quartz.service.impl;

import com.facedamon.smart.common.support.Convert;
import com.facedamon.smart.quartz.domain.JobLog;
import com.facedamon.smart.quartz.mapper.JobLogMapper;
import com.facedamon.smart.quartz.service.IJobLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:    定时任务调度日志信息 service
 * @Author:         facedamon
 * @CreateDate:     2018/11/26 15:28
 * @UpdateUser:     facedamon
 * @UpdateDate:     2018/11/26 15:28
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
@Service
public class JobLogServiceImpl implements IJobLogService {

    @Autowired
    private JobLogMapper jobLogMapper;

    /**
     * 获取quartz调度器日志的计划任务
     * @param jobLog
     * @return
     */
    @Override
    public List<JobLog> selectJobLogList(JobLog jobLog) {
        return jobLogMapper.selectJobLogList(jobLog);
    }

    /**
     * 通过调度任务日志ID查询调度信息
     * @param jobLogId
     * @return
     */
    @Override
    public JobLog selectJobLogById(Long jobLogId) {
        return jobLogMapper.selectJobLogById(jobLogId);
    }

    /**
     * 新增任务日志
     * @param jobLog
     */
    @Override
    public int addJobLog(JobLog jobLog) {
        return jobLogMapper.insertJobLog(jobLog);
    }

    /**
     * 批量删除调度日志信息
     * @param ids
     * @return
     */
    @Override
    public int deleteJobLogByIds(String ids) {
        return jobLogMapper.deleteJobLogByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除任务日志
     * @param jobId
     * @return
     */
    @Override
    public int deleteJobLogById(Long jobId) {
        return jobLogMapper.deleteJobLogById(jobId);
    }

    /**
     * 清空任务日志
     */
    @Override
    public void cleanJobLog() {
        jobLogMapper.cleanJobLog();
    }
}
