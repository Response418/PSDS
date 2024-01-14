package com.example.psds.personal_account.config;

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

import javax.sql.DataSource;

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
    public LocalContainerEntityManagerFactoryBean paEntityManagerFactory(EntityManagerFactoryBuilder builder, DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.example.psds.personal_account.model")
                .persistenceUnit("pa")
                .build();
    }


    @Bean(name = "paTransactionManager")
    public PlatformTransactionManager paTransactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}
