package com.natwest.hiring.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.natwest.hiring.repository")
public class PersistenceJPAConfig {

    @Qualifier("datasource")
    @Autowired
    DataSource dataSource;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com.natwest.hiring.entity");

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());

        return em;
    }

    Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
//        properties.setProperty("javax.persistence.jdbc.url", "jdbc:mysql://localhost:3306/AccountTransaction");
//        properties.setProperty("javax.persistence.jdbc.user", "root");
//        properties.setProperty("javax.persistence.jdbc.password", "root1234");
//        properties.setProperty("javax.persistence.jdbc.driver", "com.mysql.cj.jdbc.Driver");
        return properties;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

//    @Bean
//    public LocalEntityManagerFactoryBean entityManagerFactory() {
//        LocalEntityManagerFactoryBean factoryBean = new LocalEntityManagerFactoryBean();
////        HibernatePersistenceProvider hibernatePersistenceProvider = new HibernatePersistenceProvider();
////        hibernatePersistenceProvider.createEntityManagerFactory("AccTransact", additionalProperties());
////        factoryBean.setPersistenceProvider(hibernatePersistenceProvider);
//        factoryBean.setPersistenceUnitName("AccTransact");
//        return factoryBean;
//    }
//
//    @Bean
//    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
//        JpaTransactionManager transactionManager = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(entityManagerFactory);
//
//        return transactionManager;
//    }
}