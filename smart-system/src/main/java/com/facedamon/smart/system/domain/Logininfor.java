package com.facedamon.smart.system.domain;

import com.facedamon.smart.common.annotation.Excel;
import com.facedamon.smart.common.base.BaseEntity;
import lombok.Builder;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * @Description:    系统访问记录
 * @Author:         facedamon
 * @CreateDate:     2018/10/16 下午3:59
 * @UpdateUser:     facedamon
 * @UpdateDate:     2018/10/16 下午3:59
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
@Data
@Builder
@Alias("Logininfor")
public class Logininfor extends BaseEntity {

    /**
     * 序号
     */
    @Excel(name = "序号")
    private long infoId;

    /**
     * 登录账号
     */
    @Excel(name = "用户账号")
    private String loginName;

    /**
     * 登录状态 0成功 1失败
     */
    @Excel(name = "登录状态",isDict = true,dictType = "sys_common_status")
    private String status;

    /**
     * 登录地址
     */
    @Excel(name = "登录地址")
    private String ipaddr;

    /**
     * 登录地点
     */
    @Deprecated
    private String loginLocation;

    /**
     * 浏览器类型
     */
    @Excel(name = "浏览器")
    private String browser;

    /**
     * 操作系统
     */
    @Excel(name = "操作系统")
    private String os;

    /**
     * 提示消息
     */
    @Excel(name = "操作信息")
    private String msg;

    /**
     * 访问时间
     */
    @Excel(name = "访问时间")
    private Date loginTime;
}