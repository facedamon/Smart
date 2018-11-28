package com.facedamon.smart.quartz.domain;

import com.facedamon.smart.common.annotation.Excel;
import com.facedamon.smart.common.base.BaseEntity;
import lombok.Builder;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * @Description:    定是个hi任务调度日志
 * @Author:         facedamon
 * @CreateDate:     2018/11/26 14:47
 * @UpdateUser:     facedamon
 * @UpdateDate:     2018/11/26 14:47
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
@Data
@Builder
@Alias("JobLog")
public class JobLog extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Excel(name = "日志序号")
    private Long jobLogId;

    @Excel(name = "任务名称")
    private String jobName;

    @Excel(name = "任务组名")
    private String jobGroup;

    @Excel(name = "任务方法")
    private String methodName;

    @Excel(name = "方法参数")
    private String methodParams;

    @Excel(name = "日志信息")
    private String jobMessage;

    @Excel(name = "执行状态",isDict = true,dictType = "sys_normal_disable")
    private String status;

    @Excel(name = "异常信息")
    private String exceptionInfo;
}
