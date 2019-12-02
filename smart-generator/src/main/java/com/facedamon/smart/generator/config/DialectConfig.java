package com.facedamon.smart.generator.config;

import com.facedamon.smart.common.utils.StringUtils;
import com.facedamon.smart.generator.dialect.convert.Dialect;
import com.facedamon.smart.generator.dialect.convert.MySql;
import com.facedamon.smart.generator.dialect.convert.Oracle;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @Description:
 * @Author: facedamon
 * @CreateDate: 2018/11/20 16:04
 * @UpdateUser: facedamon
 * @UpdateDate: 2018/11/20 16:04
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Configuration
@Slf4j
public class DialectConfig {

    @Autowired
    private DatabaseIdProvider databaseIdProvider;

    @Autowired
    private DataSource dataSource;

    private String mysql = "mysql";
    private String oracle = "oracle";

    @Bean
    public Dialect dialect() {
        try {
            String databaseId = databaseIdProvider.getDatabaseId(dataSource);
            if (StringUtils.equalsIgnoreCase(databaseId, mysql)) {
                return mySql();
            } else if (StringUtils.equalsIgnoreCase(databaseId, oracle)) {
                return oracle();
            }
        } catch (SQLException e) {
            log.error("加载databaseId异常", e.getMessage());
            throw new RuntimeException(e);
        }
        return null;
    }

    @Bean
    public MySql mySql() {
        return new MySql();
    }

    @Bean
    public Oracle oracle() {
        return new Oracle();
    }
}
