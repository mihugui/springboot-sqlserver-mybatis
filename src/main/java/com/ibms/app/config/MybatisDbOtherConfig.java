package com.ibms.app.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @ClassName: MybatisDbOtherConfig
 * @User: PWT
 * @Date: 2019/1/10 10:10
 * @Version 1.0
 * Description: No Description
 */
@Configuration
@MapperScan(basePackages = "com.ibms.app.dao.other", sqlSessionFactoryRef = "otherSqlSessionFactory")
public class MybatisDbOtherConfig {

        @Bean(name = "otherDataSource")
        @ConfigurationProperties(prefix = "spring.datasource.other")
        public DataSource dataSource() {
            return DataSourceBuilder.create().build();
        }

        @Bean(name = "otherTransactionManager")
        public DataSourceTransactionManager transactionManager(@Qualifier("otherDataSource") DataSource dataSource) {
            return new DataSourceTransactionManager(dataSource);

        }

        @Bean(name = "otherSqlSessionFactory")
        public SqlSessionFactory basicSqlSessionFactory(@Qualifier("otherDataSource") DataSource basicDataSource) throws Exception {
            SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
            factoryBean.setDataSource(basicDataSource);
            factoryBean.setMapperLocations(
                    new PathMatchingResourcePatternResolver().getResources("classpath:mapping/other/*.xml"));
            return factoryBean.getObject();
        }

        @Bean(name = "otherSqlSessionTemplate")
        public SqlSessionTemplate testSqlSessionTemplate(
                @Qualifier("otherSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
            return new SqlSessionTemplate(sqlSessionFactory);
        }
}
