package com.facedamon.smart.system.domain;

import com.facedamon.smart.common.annotation.Excel;
import com.facedamon.smart.common.base.BaseEntity;
import lombok.Builder;
import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * @Description:    字典数据表
 * @Author:         facedamon
 * @CreateDate:     2018/10/29 16:48
 * @UpdateUser:     facedamon
 * @UpdateDate:     2018/10/29 16:48
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
@Data
@Builder
@Alias("DictData")
public class DictData extends BaseEntity {

    /**
     * 字典编码
     */
    @Excel(name = "字典编码")
    private Long dictCode;

    /**
     * 字典排序
     */
    @Excel(name = "字典排序")
    private Long dictSort;

    /**
     * 字典标签
     */
    @Excel(name = "字典标签")
    private String dictLabel;

    /**
     * 字典值
     */
    @Excel(name = "字典值")
    private String dictValue;

    /**
     * 字典类型
     */
    @Excel(name = "字典类型")
    private String dictType;
    /**
     * 样式属性
     */
    private String cssClass;

    /**
     * 表格回显样式
     */
    @Excel(name = "样式")
    private String listClass;

    /**
     * 是否默认
     */
    @Excel(name = "是否默认")
    private String isDefault;

    /**
     * 状态
     */
    @Excel(name = "状态",isDict = true,dictType = "sys_normal_disable")
    private String status;
}
