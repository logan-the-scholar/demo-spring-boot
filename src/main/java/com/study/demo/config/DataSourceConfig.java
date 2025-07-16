package com.study.demo.config;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

//@Configuration
//@EnableTransactionManagement
//@EnableConfigurationProperties(DataSourceConfigProperties.class)
public class DataSourceConfig {

//    DataSourceConfigProperties configProperties;
//
//    public DataSourceConfig(@Qualifier("dataSourceConfigProperties") DataSourceConfigProperties configProperties) {
//        this.configProperties = configProperties;
//    }
//
//    @Bean
//    public DataSource dataSource() {
//        HikariDataSource dataSource = new HikariDataSource();
//
//        dataSource.setJdbcUrl(configProperties.getUrl());
//        dataSource.setUsername(configProperties.getUsername());
//        dataSource.setPassword(configProperties.getPassword());
//        dataSource.setDriverClassName("org.postgresql.Driver");
//        return dataSource;
//    }
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource,
//                                                                       JpaVendorAdapter jpaVendorAdapter) {
//
//        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//        em.setDataSource(dataSource);
//        em.setPackagesToScan("com.study.demo.modules.user", "com.study.demo.modules.workspace");
//        em.setJpaVendorAdapter(jpaVendorAdapter);
//        return em;
//    }
//
//    @Bean
//    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
//        return new JpaTransactionManager(emf);
//    }
//
//    @Bean
//    public JpaVendorAdapter jpaVendorAdapter() {
//        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
//        adapter.setDatabasePlatform("org.hibernate.dialect.PostgreSQLDialect");
//        adapter.setShowSql(true);
//        adapter.setGenerateDdl(true);
//
//        return adapter;
//    }
}