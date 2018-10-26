package com.facedamon.smart.system.doamin;

import com.facedamon.smart.common.base.BaseEntity;
import com.facedamon.smart.common.enums.OnlineStatus;
import lombok.Builder;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * @Description: 当前在线会话
 * @Author: facedamon
 * @CreateDate: 2018/10/6 下午3:39
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/10/6 下午3:39
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Data
@Alias("UserOnline")
@Builder
public class UserOnline extends BaseEntity {

    /**
     * seesion id
     */
    private String sessionId;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 登录名称
     */
    private String loginName;

    /**
     * 登录IP
     */
    private String ipaddr;

    /**
     * 登录地址
     */
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
     * 会话创建时间
     */
    private Date startTimestamp;

    /**
     * 上次访问时间
     */
    private Date lastAccessTime;

    /**
     * 会话超时his 间，单位min
     */
    private Long expireTime;

    /**
     * 在线状态
     */
    private OnlineStatus status = OnlineStatus.ON_LINE;

}