package com.example.studyproject.config;

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

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "test1EntityManager",
        transactionManagerRef = "test1TransactionManager",
        basePackages = "com.example.studyproject.repository.test1"
)
public class Test1JpaConfig {

    @Autowired
    private Environment env;

    String[] packageToScan = {"com.example.studyproject.entity.test1"};

    @Bean
    public DataSource test1DataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("spring.test1.datasource.driverClassName"));
        dataSource.setUrl(env.getProperty("spring.test1.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.test1.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.test1.datasource.password"));
        return dataSource;
    }

    @Primary
    @Bean(name="test1EntityManager")
    public LocalContainerEntityManagerFactoryBean guidanceEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(test1DataSource());
        em.setPackagesToScan(packageToScan);

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String,Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto",env.getProperty("hibernate.dialect"));
        em.setJpaPropertyMap(properties);

        return em;
    }

    @Primary
    @Bean(name="test1TransactionManager")
    public PlatformTransactionManager guidanceTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(guidanceEntityManagerFactory().getObject());
        return transactionManager;
    }
}
