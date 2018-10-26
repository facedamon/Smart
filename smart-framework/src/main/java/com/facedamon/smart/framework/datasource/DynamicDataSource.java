package com.facedamon.smart.framework.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @Description: 多数据切换核心处理器
 * @Author: facedamon
 * @CreateDate: 2018/8/14 15:43
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/8/14 15:43
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceHolder.getDB();
    }

    public DynamicDataSource(DataSource defaultTargetDataSource, Map<Object, Object> targetDataSources) {
        super.setDefaultTargetDataSource(defaultTargetDataSource);
        super.setTargetDataSources(targetDataSources);
        super.afterPropertiesSet();
    }
}