package com.facedamon.smart.system.domain;

import com.facedamon.smart.common.annotation.Excel;
import com.facedamon.smart.common.base.BaseEntity;
import lombok.Builder;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * @Description:    操作日志记录表
 * @Author:         facedamon
 * @CreateDate:     2018/10/29 10:28
 * @UpdateUser:     facedamon
 * @UpdateDate:     2018/10/29 10:28
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
@Data
@Builder
@Alias("OperLog")
public class OperLog extends BaseEntity {

    /**
     * 日志主键
     */
    @Excel(name = "日志序号")
    private Long operId;

    /**
     * 操作模块
     */
    @Excel(name = "操作模块")
    private String model;

    /**
     * 业务类型
     */
    @Excel(name = "业务类型")
    private Integer businessType;

    /**
     * 请求方法
     */
    @Excel(name = "请求参数")
    private String method;

    /**
     * 操作类别
     */
    @Excel(name = "操作类别",isDict = true, dictType = "sys_oper_type")
    private Integer operatorType;

    /**
     * 操作人员
     */
    @Excel(name = "操作人员")
    private String operName;

    /**
     * 部门名称
     */
    @Excel(name = "部门")
    private String deptName;

    /**
     * 请求URL
     */
    @Excel(name = "请求链接")
    private String operUrl;

    /**
     * 操作地址
     */
    @Excel(name = "操作地址")
    private String operIp;

    /**
     * 操作地点
     */
    @Deprecated
    private String operLocation;

    /**
     * 请求参数
     */
    @Excel(name = "请求参数")
    private String operParam;

    /**
     * 操作状态
     */
    @Excel(name = "操作状态",isDict = true,dictType = "sys_normal_disable")
    private Integer status;

    /**
     *  错误消息
     */
    @Excel(name = "错误消息")
    private String errorMsg;

    /**
     * 操作时间
     */
    @Excel(name = "操作时间")
    private Date operTime;
}
