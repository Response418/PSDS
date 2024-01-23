package com.example.psds.knowledge_base.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.example.psds.knowledge_base.repository",
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
    public LocalContainerEntityManagerFactoryBean kbEntityManagerFactory(EntityManagerFactoryBuilder builder, DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.example.psds.knowledge_base.model")
                .persistenceUnit("kb")
                .build();
    }

    @Primary
    @Bean(name = "kbTransactionManager")
    public PlatformTransactionManager kbTransactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}

