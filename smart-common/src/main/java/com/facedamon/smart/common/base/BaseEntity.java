package com.facedamon.smart.common.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * @Description: 所有实体的共同域
 * @Author: facedamon
 * @CreateDate: 2018/8/15 16:05
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/8/15 16:05
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Data
public class BaseEntity implements Serializable {
    /**
     * 搜索词
     */
    private String searchValue;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 请求参数
     */
    private Map<String, Object> params;
}