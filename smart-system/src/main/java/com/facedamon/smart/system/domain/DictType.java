package com.facedamon.smart.system.domain;

import com.facedamon.smart.common.annotation.Excel;
import com.facedamon.smart.common.base.BaseEntity;
import lombok.Builder;
import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * @Description:    字典类型表
 * @Author:         facedamon
 * @CreateDate:     2018/10/29 16:46
 * @UpdateUser:     facedamon
 * @UpdateDate:     2018/10/29 16:46
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
@Data
@Builder
@Alias("DictType")
public class DictType extends BaseEntity {

    /**
     * 字典主键
     */
    @Excel(name = "字典编号")
    private Long dictId;

    /**
     * 字典名称
     */
    @Excel(name = "字典名称")
    private String dictName;

    /**
     * 字典类型
     */
    @Excel(name = "字典类型")
    private String dictType;

    /**
     * 状态(0:正常，1：停用)
     */
    @Excel(name = "状态", isDict = true, dictType = "sys_normal_disable")
    private String status;
}
