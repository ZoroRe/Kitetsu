package me.zoro.kitetsu.mysql.mybatis;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author luguanquan
 * @date 2020-03-14 10:42
 *
 * 使用阿里的 Druid 数据库连接池,但现在单数据源，直接使用 application.yaml 的配置就足够
 */
@Deprecated
//@Configuration
public class DataSourceConfig {
//    /**
//     * druid的过滤器设置
//     */
//    @Value("${spring.datasource.druid.filters:}")
//    private String filters;
//
//    @Primary
//    @Bean("dbMysql")
//    @ConfigurationProperties(prefix = "spring.datasource")
//    public DataSource dataSourceMysql() {
//        DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
//        if (!StringUtils.isEmpty(filters)) {
//            try {
//                dataSource.setFilters(filters);
//            } catch (SQLException ex) {
//                ex.printStackTrace();
//            }
//        }
//        return dataSource;
//    }
}
