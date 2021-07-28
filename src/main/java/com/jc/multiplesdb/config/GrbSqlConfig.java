package com.jc.multiplesdb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
/*@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "grbEntityManagerFactory",
        transactionManagerRef = "grbTransactionManager",
        basePackages = {"com.jc.multiplesdb"})*/
public class GrbSqlConfig {

    @Autowired
    private Environment env;

    @Bean(name = "grbDataSource")
    public DataSource grbDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(env.getProperty("spring.grb.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.grb.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.grb.datasource.password"));
        dataSource.setDriverClassName(env.getProperty("spring.grb.datasource.driver-class-name"));
        return dataSource;
    }

    @Bean(name = "grbEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(){
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(grbDataSource());
        em.setPackagesToScan("com.jc.multiplesdb");
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        Map<String,Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto",env.getProperty("spring.grb.jpa.hibernate.ddl-auto"));
        properties.put("hibernate.show-sql",env.getProperty("spring.grb.show-sql"));
        properties.put("hibernate.dialect",env.getProperty("spring.grb.jpa.database-platform"));

        em.setJpaPropertyMap(properties);
        return em;
    }

    @Bean(name = "grbTransactionManager")
    public PlatformTransactionManager transactionManager(){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());

        return transactionManager;
    }

}
