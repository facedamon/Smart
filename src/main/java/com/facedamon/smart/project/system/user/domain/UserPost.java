package com.facedamon.smart.project.system.user.domain;

import lombok.Builder;
import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * @Description:    用户-岗位对应关系
 * @Author:         facedamon
 * @CreateDate:     2018/9/30 下午9:13
 * @UpdateUser:     facedamon
 * @UpdateDate:     2018/9/30 下午9:13
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
@Data
@Alias("UserPost")
@Builder
public class UserPost{
    private Long userId;

    private Long  postId;
}
