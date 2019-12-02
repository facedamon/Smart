package com.facedamon.smart.quartz.service;

import com.facedamon.smart.quartz.domain.Job;

import java.util.List;

/**
 * @Description: 调度 service
 * @Author: facedamon
 * @CreateDate: 2018/11/26 15:14
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/11/26 15:14
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public interface IJobService {

    /**
     * 获取调度任务计划
     *
     * @param job
     * @return
     */
    List<Job> selectJobList(Job job);

    /**
     * 根据ID查询调度信息
     *
     * @param jobId
     * @return
     */
    Job selectJobById(Long jobId);

    /**
     * 暂停任务
     *
     * @param job
     * @return
     */
    int pauseJob(Job job);

    /**
     * 恢复任务
     *
     * @param job
     * @return
     */
    int resumeJob(Job job);

    /**
     * 删除任务，对应的trigger也将被删除
     *
     * @param job
     * @return
     */
    int deleteJob(Job job);

    /**
     * 批量删除调度信息
     *
     * @param ids
     */
    void deleteJobByIds(String ids);

    /**
     * 任务调度状态修改
     *
     * @param job
     * @return
     */
    int changeStatus(Job job);

    /**
     * 新增任务表达式
     *
     * @param job
     * @return
     */
    int insertJobCron(Job job);

    /**
     * 更新任务时间表达式
     *
     * @param job
     * @return
     */
    int updateJobCron(Job job);

    /**
     * 立即运行
     *
     * @param job
     * @return
     */
    int run(Job job);
}