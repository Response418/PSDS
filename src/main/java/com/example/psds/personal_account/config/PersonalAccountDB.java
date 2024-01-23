package com.example.psds.personal_account.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.example.psds.personal_account.Repository",
        entityManagerFactoryRef = "paEntityManagerFactory",
        transactionManagerRef = "paTransactionManager"
)
public class PersonalAccountDB {

    @Bean(name = "paDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.pa")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }


    @Bean(name = "paEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean paEntityManagerFactory() {

        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("com.example.psds.personal_account.Model");

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());

        return em;
    }

    @Bean(name = "paTransactionManager")
    public PlatformTransactionManager paTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(paEntityManagerFactory().getObject());

        return transactionManager;
    }

    @Bean(name = "paExceptionTranslation")
    public PersistenceExceptionTranslationPostProcessor paExceptionTranslation(){
        return new PersistenceExceptionTranslationPostProcessor();
    }


    @Value("${spring.datasource.pa.jpa.hibernate.ddl-auto}")
    private String hibernateDdAuto;

    @Value("${spring.datasource.pa.jpa.properties.hibernate.dialect}")
    private String hibernateDialect;
    Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", hibernateDdAuto);
        properties.setProperty("hibernate.dialect", hibernateDialect);

        return properties;
    }

}
