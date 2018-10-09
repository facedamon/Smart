package com.facedamon.smart.project.system.dept.domain;

import com.facedamon.smart.core.web.domain.BaseEntity;
import lombok.Builder;
import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * @Description:    菜单 domain
 * @Author:         facedamon
 * @CreateDate:     2018/10/9 15:36
 * @UpdateUser:     facedamon
 * @UpdateDate:     2018/10/9 15:36
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
@Data
@Alias("Dept")
@Builder
public class Dept extends BaseEntity {

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 菜单名称
     */
    private String deptName;

    /**
     * 父部门ID
     */
    private Long parentId;

    /**
     * 祖级列表
     */
    private String ancestors;

    /**
     * 显示顺序
     */
    private String orderNum;

    /**
     * 负责人
     */
    private String leader;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 部门状态：0：正常，1：停用
     */
    private String status;

    /**
     * 删除标志：0：存在，2: 删除
     */
    private String delFlag;

    /**
     * 父部门名称
     */
    private String parentName;
}
