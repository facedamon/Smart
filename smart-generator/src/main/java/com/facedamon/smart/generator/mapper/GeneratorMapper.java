package com.facedamon.smart.generator.mapper;

import com.facedamon.smart.generator.domain.ColumnInfo;
import com.facedamon.smart.generator.domain.TableInfo;

import java.util.List;

/**
 * @Description:    逆向mapper
 * @Author:         facedamon
 * @CreateDate:     2018/11/20 10:18
 * @UpdateUser:     facedamon
 * @UpdateDate:     2018/11/20 10:18
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
public interface GeneratorMapper {

    /**
     * 查询表信息
     * @param tableInfo
     * @return
     */
    List<TableInfo> selectTableList(TableInfo tableInfo);

    /**
     * 根据表名查询信息
     * @param tableName
     * @return
     */
    TableInfo selectTableByName(String tableName);

    /**
     * 根据表名查询列信息
     * @param tableName
     * @return
     */
    List<ColumnInfo> selectTableColumnsByName(String tableName);
}
