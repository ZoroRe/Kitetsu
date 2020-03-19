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
 * <p>
 * 使用阿里的 Druid 数据库连接池,但现在单数据源，直接使用 application.yaml 的配置就足够,所以原本不需要这个类，后来增加多数据源
 * 就需要配置多个
 * </p>
 */
@Configuration
public class DataSourceConfig {
	/**
	 * druid的过滤器设置
	 */
	@Value("${spring.datasource.druid.filters:}")
	private String filters;

	@Primary
	@Bean("dataSource")
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource dataSourceMysql() {
		DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
		if (!StringUtils.isEmpty(filters)) {
			try {
				dataSource.setFilters(filters);
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return dataSource;
	}

	/**
	 * 注意下面的 prefix 和配置中设置的数据源一致
	 * @return
	 */
	@Bean("dataSource2")
	@ConfigurationProperties(prefix = "spring.datasource2")
	public DataSource dataSourceOracle() {
		DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
		if (!StringUtils.isEmpty(filters)) {
			try {
				dataSource.setFilters(filters);
				dataSource.setValidationQuery("SELECT 1 FROM SYS.DUAL");
				dataSource.setTestOnBorrow(true);
				//dataSource.setTestOnReturn(true);
				dataSource.setTestWhileIdle(true);
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return dataSource;
	}
}
