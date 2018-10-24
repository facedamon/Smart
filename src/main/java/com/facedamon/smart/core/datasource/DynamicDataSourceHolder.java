package com.facedamon.smart.core.datasource;

import com.facedamon.smart.core.aspj.constants.DataSourceName;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 多数据源辅助类
 * @Author: facedamon
 * @CreateDate: 2018/8/14 15:45
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/8/14 15:45
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Slf4j
public class DynamicDataSourceHolder {
    /**
     * 当前线程数据源
     */
    private static final ThreadLocal<DataSourceName> LOCAL_DATASOURCE = new ThreadLocal<>();

    /**
     * 设置数据源
     *
     * @param dbType
     */
    public static void setDB(DataSourceName dbType) {
        log.info("切换到{}数据源", dbType);
        LOCAL_DATASOURCE.set(dbType);
    }

    /**
     * 获取数据源
     *
     * @return
     */
    public static DataSourceName getDB() {
        return LOCAL_DATASOURCE.get();
    }

    /**
     * 清空数据源
     */
    public static void remove() {
        LOCAL_DATASOURCE.remove();
    }
}
