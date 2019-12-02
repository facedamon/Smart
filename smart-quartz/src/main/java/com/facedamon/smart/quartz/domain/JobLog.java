package com.facedamon.smart.quartz.domain;

import com.facedamon.smart.common.base.BaseEntity;
import lombok.Builder;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * @Description: 定是个hi任务调度日志
 * @Author: facedamon
 * @CreateDate: 2018/11/26 14:47
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/11/26 14:47
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Data
@Builder
@Alias("JobLog")
public class JobLog extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long jobLogId;

    private String jobName;

    private String jobGroup;

    private String methodName;

    private String methodParams;

    private String jobMessage;

    private String status;

    private String exceptionInfo;
}
