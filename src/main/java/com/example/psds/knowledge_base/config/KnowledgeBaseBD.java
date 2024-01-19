package com.example.psds.knowledge_base.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
@EnableJpaRepositories(
        basePackages = "com.example.psds.knowledge_base.Repository",
        entityManagerFactoryRef = "kbEntityManagerFactory",
        transactionManagerRef = "kbTransactionManager"
)
public class KnowledgeBaseBD {
    @Primary
    @Bean(name = "kbDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.kb")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "kbEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean kbEntityManagerFactory() {

        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("com.example.psds.knowledge_base.Model");

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());

        return em;
    }
    @Primary
    @Bean(name = "kbTransactionManager")
    public PlatformTransactionManager kbTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(kbEntityManagerFactory().getObject());

        return transactionManager;
    }
    @Primary
    @Bean(name = "kbExceptionTranslation")
    public PersistenceExceptionTranslationPostProcessor kbExceptionTranslation(){
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Value("${spring.datasource.kb.jpa.hibernate.ddl-auto}")
    private String hibernateDdAuto;

    @Value("${spring.datasource.kb.jpa.properties.hibernate.dialect}")
    private String hibernateDialect;
    @Primary
    Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", hibernateDdAuto);
        properties.setProperty("hibernate.dialect", hibernateDialect);

        return properties;
    }
}

