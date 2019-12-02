package com.facedamon.smart.system.domain;

import com.facedamon.smart.common.base.BaseEntity;
import lombok.Builder;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * @Description: 系统访问记录
 * @Author: facedamon
 * @CreateDate: 2018/10/16 下午3:59
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/10/16 下午3:59
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Data
@Builder
@Alias("Logininfor")
public class Logininfor extends BaseEntity {

    /**
     * 序号
     */
    private long infoId;

    /**
     * 登录账号
     */
    private String loginName;

    /**
     * 登录状态 0成功 1失败
     */
    private String status;

    /**
     * 登录地址
     */
    private String ipaddr;

    /**
     * 登录地点
     */
    @Deprecated
    private String loginLocation;

    /**
     * 浏览器类型
     */
    private String browser;

    /**
     * 操作系统
     */
    private String os;

    /**
     * 提示消息
     */
    private String msg;

    /**
     * 访问时间
     */
    private Date loginTime;
}