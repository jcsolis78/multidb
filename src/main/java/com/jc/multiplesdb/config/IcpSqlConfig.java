package com.jc.multiplesdb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "icpEntityManagerFactory",
        transactionManagerRef = "icpTransactionManager",
        basePackages = {"com.jc.multiplesdb"})
public class IcpSqlConfig {

    @Autowired
    private Environment env;

    @Bean(name = "icpDataSource")
    @Primary
    public DataSource icpDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(env.getProperty("spring.icp.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.icp.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.icp.datasource.password"));
        dataSource.setDriverClassName(env.getProperty("spring.icp.datasource.driver-class-name"));
        return dataSource;
    }

    @Bean(name = "icpEntityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(){
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(icpDataSource());
        em.setPackagesToScan("com.jc.multiplesdb");
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        Map<String,Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto",env.getProperty("spring.icp.jpa.hibernate.ddl-auto"));
        properties.put("hibernate.show-sql",env.getProperty("spring.icp.show-sql"));
        properties.put("hibernate.dialect",env.getProperty("spring.icp.jpa.database-platform"));

        em.setJpaPropertyMap(properties);
        return em;
    }

    @Bean(name = "icpTransactionManager")
    @Primary
    public PlatformTransactionManager transactionManager(){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());

        return transactionManager;
    }

}
