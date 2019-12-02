package com.facedamon.smart.generator.domain;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

/**
 * @Description: 表列信息
 * @Author: facedamon
 * @CreateDate: 2018/11/20 10:17
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/11/20 10:17
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Getter
@Setter
@Alias("ColumnInfo")
public class ColumnInfo {

    /**
     * 字段名称
     */


    private String columnName;

    /**
     * 字段类型
     */


    private String dataType;

    /**
     * 字段描述
     */


    private String columnComment;

    /**
     * Java类型
     */


    private String javaType;

    /**
     * Java属性名称首字母大写
     */

    private String attrName;

    /**
     * Java属性名称首字母小写
     */

    private String attrNameLower;

    public ColumnInfo() {
    }
}
