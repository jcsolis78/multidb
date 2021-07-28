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
@EnableJpaRepositories(entityManagerFactoryRef = "grcEntityManagerFactory",
        transactionManagerRef = "grcTransactionManager",
        basePackages = {"com.jc.multiplesdb"})*/
public class GrcSqlConfig {

    @Autowired
    private Environment env;

    @Bean(name = "grcDataSource")
    public DataSource grcDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(env.getProperty("spring.grc.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.grc.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.grc.datasource.password"));
        dataSource.setDriverClassName(env.getProperty("spring.grc.datasource.driver-class-name"));
        return dataSource;
    }

    @Bean(name = "grcEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(){
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(grcDataSource());
        em.setPackagesToScan("com.jc.multiplesdb");
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        Map<String,Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto",env.getProperty("spring.grc.jpa.hibernate.ddl-auto"));
        properties.put("hibernate.show-sql",env.getProperty("spring.grc.show-sql"));
        properties.put("hibernate.dialect",env.getProperty("spring.grc.jpa.database-platform"));

        em.setJpaPropertyMap(properties);
        return em;
    }

    @Bean(name = "grcTransactionManager")
    public PlatformTransactionManager transactionManager(){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());

        return transactionManager;
    }

}
