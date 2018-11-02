package com.facedamon.smart.system.doamin;

import com.facedamon.smart.common.base.BaseEntity;
import lombok.Builder;
import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * @Description:    岗位表
 * @Author:         facedamon
 * @CreateDate:     2018/10/30 17:20
 * @UpdateUser:     facedamon
 * @UpdateDate:     2018/10/30 17:20
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
@Data
@Builder
@Alias("Post")
public class Post extends BaseEntity {

    /**
     * 岗位Id
     */
    private Long postId;

    /**
     * 岗位编码
     */
    private String postCode;

    /**
     * 岗位名称
     */
    private String postName;

    /**
     * 岗位排序
     */
    private String postSort;

    /**
     * 状态
     */
    private String status;

    /**
     * 用户是否存在此岗位标识，默认不存在
     */
    private boolean flag = false;
}
