package com.facedamon.smart.quartz.domain;

import com.facedamon.smart.common.base.BaseEntity;
import com.facedamon.smart.common.constant.ScheduleConstants;
import lombok.*;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * @Description: 定时任务调度表
 * 因为需要持久化所以实现序列化接口
 * @Author: facedamon
 * @CreateDate: 2018/11/26 14:38
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/11/26 14:38
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Alias("Job")
public class Job extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long jobId;

    private String jobName;

    private String jobGroup;

    private String methodName;

    private String methodParams;

    private String cronExpression;

    private String status;

    private String misfirePolicy = ScheduleConstants.MISFIRE_DEFAULT;
}
