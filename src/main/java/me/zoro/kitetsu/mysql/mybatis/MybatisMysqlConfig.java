package me.zoro.kitetsu.mysql.mybatis;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @author luguanquan
 * @date 2020-03-14 10:44
 * <p>
 * 使用了 mybatis-spring-boot-starter,包含了一些配置，所以不用这个也不影响,如果直接用 mybatis 需要增加一些配置
 */
@Deprecated
//@Configuration
//@MapperScan(basePackages = "me.zoro.kitetsu.mysql.mybatis", sqlSessionFactoryRef = "sqlSessionFactory1")
public class MybatisMysqlConfig {

//	@Autowired
//	@Qualifier("dbMysql")
//	private DataSource dbMysql;
//
//	@Bean
//	// 此注解表明有限使用这个备注 @Primary
//	@Primary
//	public SqlSessionFactory sqlSessionFactory1() throws Exception {
//		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
//		factoryBean.setDataSource(dbMysql);
//		org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
//		configuration.setMapUnderscoreToCamelCase(true);
//		factoryBean.setConfiguration(configuration);
//		return factoryBean.getObject();
//	}

//	@Bean
//	@Primary
//	public SqlSessionTemplate sqlSessionTemplate1() throws Exception {
//		SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory1());
//		return sqlSessionTemplate;
//	}
}
