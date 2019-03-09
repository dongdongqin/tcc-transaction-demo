package com.xxx.tcc.config;

import com.alibaba.dubbo.remoting.TimeoutException;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mengyun.tcctransaction.spring.recover.DefaultRecoverConfig;
import org.mengyun.tcctransaction.spring.repository.SpringJdbcTransactionRepository;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;


@Configuration
public class DataSources2Config {

    @Bean(name = "secondaryDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.secondary")
    public DataSource testDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "secondarySqlSessionFactory")
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("secondaryDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }

    @Bean(name = "secondaryTransactionManager")
    public DataSourceTransactionManager testTransactionManager(@Qualifier("secondaryDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "secondarySqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("secondarySqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    public SpringJdbcTransactionRepository transactionRepository() {
        SpringJdbcTransactionRepository springJdbcTransactionRepository = new SpringJdbcTransactionRepository();
        springJdbcTransactionRepository.setDomain("ORDER");
        springJdbcTransactionRepository.setTbSuffix("_ORD");
        springJdbcTransactionRepository.setDataSource(testDataSource());
        return springJdbcTransactionRepository;

    }

    /**
     *
     *

    @Bean
    public DefaultRecoverConfig defaultRecoverConfig() {
        DefaultRecoverConfig defaultRecoverConfig = new DefaultRecoverConfig();
        defaultRecoverConfig.setMaxRetryCount(30);
        defaultRecoverConfig.setRecoverDuration(60);
        defaultRecoverConfig.setCronExpression("0/30 * * * * ?");
        Set set = new HashSet();
        set.add(TimeoutException.class);
        defaultRecoverConfig.setDelayCancelExceptions(set);
        return defaultRecoverConfig;

    }
     */

}