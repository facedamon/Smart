package com.facedamon.smart.project.system.user.domain;

import com.facedamon.smart.core.web.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

@Data
public class User extends BaseEntity {

    private Long userId;

    private Long deptId;

    private long parentId;

    private String loginName;

    private String userName;

    private String email;

    private String sex;

    private String avatar;

    private String salt;

    private String status;

    private String delFlag;

    private String loginIp;

    private Date loginDate;

    private Long[] roleIds;
}
