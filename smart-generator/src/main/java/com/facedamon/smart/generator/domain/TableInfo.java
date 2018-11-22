package com.facedamon.smart.generator.domain;

import com.facedamon.smart.common.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.util.List;

/**
 * @Description:    表结构
 * @Author:         facedamon
 * @CreateDate:     2018/11/20 9:48
 * @UpdateUser:     facedamon
 * @UpdateDate:     2018/11/20 9:48
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
@Getter
@Setter
@Alias("TableInfo")
public class TableInfo extends BaseEntity {

    /**
     * 表名
     */
    
    
    private String tableName;

    /**
     * 表描述
     */
    
    
    private String tableComment;

    /**
     * 主键
     */
    
    
    private ColumnInfo primaryKey;

    /**
     * 列信息，不包含主键
     */
    
    
    private List<ColumnInfo> columnInfos;

    /**
     * 类名首字母大写
     */
    
    private String className;

    /**
     * 类名首字母小写
     */
    
    private String classNameLower;

    public TableInfo() {
    }

    public ColumnInfo getColumnsLast(){
        ColumnInfo columnInfo = null;
        if (null != columnInfos && !columnInfos.isEmpty()){
            /**
             * 默认第一个字段为主键
             */
            columnInfo = columnInfos.get(0);
        }
        return columnInfo;
    }

}
