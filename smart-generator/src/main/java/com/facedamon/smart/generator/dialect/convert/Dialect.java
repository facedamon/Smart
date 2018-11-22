package com.facedamon.smart.generator.dialect.convert;

/**
 * @Description:    方言接口
 * @Author:         facedamon
 * @CreateDate:     2018/11/20 15:30
 * @UpdateUser:     facedamon
 * @UpdateDate:     2018/11/20 15:30
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
public interface Dialect {
    /**
     * 获取对象类型
     * @param dbType
     * @return
     */
    String getJavaType(String dbType);
}
