package com.facedamon.smart.quartz.service.impl;

import com.facedamon.smart.common.constant.ScheduleConstants;
import com.facedamon.smart.common.support.Convert;
import com.facedamon.smart.quartz.domain.Job;
import com.facedamon.smart.quartz.mapper.JobMapper;
import com.facedamon.smart.quartz.service.IJobService;
import com.facedamon.smart.quartz.support.ScheduleUtils;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @Description: 定时任务调度service
 * @Author: facedamon
 * @CreateDate: 2018/11/26 15:32
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/11/26 15:32
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Service
public class JobServiceImpl implements IJobService {

    @Autowired
    private Scheduler scheduler;

    @Autowired
    private JobMapper jobMapper;

    /**
     * 初始化容器
     */
    @PostConstruct
    public void init() {
        List<Job> jobList = jobMapper.selectJobAll();
        for (Job job : jobList) {
            CronTrigger cronTrigger = ScheduleUtils.getCrontrigger(scheduler, job.getJobId());
            if (null == cronTrigger) {
                /**
                 * 创建
                 */
                ScheduleUtils.createScheduleJob(scheduler, job);
            } else {
                /**
                 * 更新
                 */
                ScheduleUtils.createScheduleJob(scheduler, job);
            }
        }
    }

    /**
     * 获取quartz调度器的计划任务列表
     *
     * @param job
     * @return
     */
    @Override
    public List<Job> selectJobList(Job job) {
        return jobMapper.selectJobList(job);
    }

    /**
     * 通过调度任务ID查询调度信息
     *
     * @param jobId
     * @return
     */
    @Override
    public Job selectJobById(Long jobId) {
        return jobMapper.selectJobById(jobId);
    }

    /**
     * 暂停任务
     *
     * @param job
     * @return
     */
    @Override
    public int pauseJob(Job job) {
        job.setStatus(ScheduleConstants.Status.PAUSE.getValue());
        int rows = jobMapper.updateJob(job);
        if (rows > 0) {
            ScheduleUtils.pauseJob(scheduler, job.getJobId());
        }
        return rows;
    }

    /**
     * 恢复任务
     *
     * @param job
     * @return
     */
    @Override
    public int resumeJob(Job job) {
        job.setStatus(ScheduleConstants.Status.NORMAL.getValue());
        int rows = jobMapper.updateJob(job);
        if (rows > 0) {
            ScheduleUtils.resumeJob(scheduler, job.getJobId());
        }
        return rows;
    }

    /**
     * 删除任务
     *
     * @param job
     * @return
     */
    @Override
    public int deleteJob(Job job) {
        int rows = jobMapper.deleteJobById(job.getJobId());
        if (rows > 0) {
            ScheduleUtils.deleteScheduleJob(scheduler, job.getJobId());
        }
        return rows;
    }

    /**
     * 批量删除调度任务
     *
     * @param ids
     */
    @Override
    public void deleteJobByIds(String ids) {
        Long[] jobIds = Convert.toLongArray(ids);
        for (Long jobId : jobIds) {
            Job job = jobMapper.selectJobById(jobId);
            deleteJob(job);
        }
    }

    /**
     * 修改任务调度状态
     *
     * @param job
     * @return
     */
    @Override
    public int changeStatus(Job job) {
        int rows = 0;
        String status = job.getStatus();
        if (ScheduleConstants.Status.NORMAL.getValue().equals(status)) {
            rows = resumeJob(job);
        } else if (ScheduleConstants.Status.PAUSE.getValue().equals(status)) {
            rows = pauseJob(job);
        }
        return rows;
    }

    /**
     * 新增任务
     *
     * @param job
     * @return
     */
    @Override
    public int insertJobCron(Job job) {
        job.setStatus(ScheduleConstants.Status.PAUSE.getValue());
        int rows = jobMapper.insertJob(job);
        if (rows > 0) {
            ScheduleUtils.createScheduleJob(scheduler, job);
        }
        return rows;
    }

    /**
     * 更新任务时间表达式
     *
     * @param job
     * @return
     */
    @Override
    public int updateJobCron(Job job) {
        int rows = jobMapper.updateJob(job);
        if (rows > 0) {
            ScheduleUtils.updateScheduleJob(scheduler, job);
        }
        return rows;
    }

    /**
     * 运行
     *
     * @param job
     * @return
     */
    @Override
    public int run(Job job) {
        return ScheduleUtils.run(scheduler, job);
    }
}
