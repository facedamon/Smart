package com.facedamon.smart.generator.dialect.convert;

import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @Description: mysql dialect
 * @Author: facedamon
 * @CreateDate: 2018/11/21 16:04
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/11/21 16:04
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Component
public class MySql extends HashMap<String, String> implements Dialect {

    public MySql() {
        put("tinyint", "Integer");
        put("smallint", "Integer");
        put("mediumint", "Integer");
        put("int", "Integer");
        put("integer", "integer");
        put("bigint", "Long");
        put("float", "Float");
        put("double", "Double");
        put("decimal", "BigDecimal");
        put("bit", "Boolean");
        put("char", "String");
        put("varchar", "String");
        put("tinytext", "String");
        put("text", "String");
        put("mediumtext", "String");
        put("longtext", "String");
        put("time", "Date");
        put("date", "Date");
        put("datetime", "Date");
        put("timestamp", "Date");
    }

    @Override
    public String getJavaType(String dbType) {
        return this.get(dbType);
    }
}
