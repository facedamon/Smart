package com.facedamon.smart.quartz.domain;

import com.facedamon.smart.common.annotation.Excel;
import com.facedamon.smart.common.base.BaseEntity;
import com.facedamon.smart.common.constant.ScheduleConstants;
import lombok.*;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * @Description:    定时任务调度表
 *                  因为需要持久化所以实现序列化接口
 * @Author:         facedamon
 * @CreateDate:     2018/11/26 14:38
 * @UpdateUser:     facedamon
 * @UpdateDate:     2018/11/26 14:38
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Alias("Job")
public class Job extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Excel(name = "任务序号")
    private Long jobId;

    @Excel(name = "任务名称")
    private String jobName;

    @Excel(name = "任务组名称")
    private String jobGroup;

    @Excel(name = "任务方法")
    private String methodName;

    @Excel(name = "方法参数")
    private String methodParams;

    @Excel(name = "执行表达式")
    private String cronExpression;

    @Excel(name = "任务状态",isDict = true,dictType = "sys_job_status")
    private String status;

    @Excel(name = "计划策略")
    private String misfirePolicy = ScheduleConstants.MISFIRE_DEFAULT;
}
