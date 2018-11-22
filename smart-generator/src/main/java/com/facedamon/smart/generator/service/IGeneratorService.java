package com.facedamon.smart.generator.service;

import com.facedamon.smart.generator.domain.TableInfo;

import java.util.List;

/**
 * @Description:    逆向接口
 * @Author:         facedamon
 * @CreateDate:     2018/11/20 10:27
 * @UpdateUser:     facedamon
 * @UpdateDate:     2018/11/20 10:27
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
public interface IGeneratorService {

    /**
     * 查询表信息
     * @param tableInfo
     * @return
     */
    List<TableInfo> selectTableList(TableInfo tableInfo);

    /**
     * 生成代码，字节码
     * @param tableName
     * @return
     */
    byte[] generatorCode(String tableName);

    /**
     * 批量生成代码，字节码
     * @param tableNames
     * @return
     */
    byte[] generatorCode(String[] tableNames);
}
