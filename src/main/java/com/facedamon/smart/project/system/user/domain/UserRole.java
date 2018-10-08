package com.facedamon.smart.project.system.user.domain;

import lombok.Builder;
import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * @Description:    用户-角色对应关系 N->1
 * @Author:         facedamon
 * @CreateDate:     2018/9/30 下午9:13
 * @UpdateUser:     facedamon
 * @UpdateDate:     2018/9/30 下午9:13
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
@Data
@Alias("UserRole")
@Builder
public class UserRole{

    private Long userId;

    private Long roleId;
}
