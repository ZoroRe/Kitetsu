package me.zoro.kitetsu.mysql.ds2;

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
 * @date 2020-03-19
 *
 * 指定这个数据源将要注入 mapper 包
 * <p>
 * 增加 ds2 这部分主要是为了展示多数据源的场景
 * </p>
 */
@Configuration
@MapperScan(basePackages = "me.zoro.kitetsu.mysql.ds2", sqlSessionFactoryRef = "sqlSessionFactory2")
public class MybatisConfig2 {

    /**
     * 这里配置数据源，通过 Qualifier 指定具体 bean
     */
    @Autowired
    @Qualifier("dataSource2")
    private DataSource dataSource;

    @Bean
    @Primary
    public SqlSessionFactory sqlSessionFactory2() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        factoryBean.setConfiguration(configuration);
        return factoryBean.getObject();
    }

    @Bean
    @Primary
    public SqlSessionTemplate sqlSessionTemplate2() throws Exception {
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory2());
        return sqlSessionTemplate;
    }
}
