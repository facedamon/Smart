package com.facedamon.smart.quartz.mapper;

import com.facedamon.smart.quartz.domain.Job;

import java.util.List;

/**
 * @Description:    调度任务信息mapper
 * @Author:         facedamon
 * @CreateDate:     2018/11/26 14:52
 * @UpdateUser:     facedamon
 * @UpdateDate:     2018/11/26 14:52
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
public interface JobMapper {

    /**
     * 查询调度任务日志
     * @param job
     * @return
     */
    List<Job> selectJobList(Job job);

    /**
     * 查询所有调度任务
     * @return
     */
    List<Job> selectJobAll();

    /**
     * 根据ID查询调度任务信息
     * @param jobId
     * @return
     */
    Job selectJobById(Long jobId);

    /**
     * 根据ID删除调度任务
     * @param jobId
     * @return
     */
    int deleteJobById(Long jobId);

    /**
     * 批量删除调度任务
     * @param ids
     * @return
     */
    int deleteJobByIds(Long[] ids);

    /**
     * 修改调度任务信息
     * @param job
     * @return
     */
    int updateJob(Job job);

    /**
     * 新增调度任务信息
     * @param job
     * @return
     */
    int insertJob(Job job);
}
