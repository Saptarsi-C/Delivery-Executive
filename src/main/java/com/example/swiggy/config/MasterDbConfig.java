/**
 * 
 */
package com.example.swiggy.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author saptarsichaurashy
 *
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "masterEntityManagerFactory", transactionManagerRef = "masterPlatformTransactionManager", basePackages = {
		"com.example.swiggy.repo" })
public class MasterDbConfig {

	/**
	 * master DataSource.
	 *
	 * @param properties
	 *            the properties
	 * @return the data source
	 */
	@Primary
	@Bean(name = "masterDataSource")
	public DataSource masterDataSource(@Qualifier("masterDataSourceProperties") DataSourceProperties properties) {
		return properties.initializeDataSourceBuilder().type(properties.getType()).build();
	}

	/**
	 * master EntityManagerFactory.
	 *
	 * @param builder
	 *            the builder
	 * @param dataSource
	 *            the data source
	 * @return the local container entity manager factory bean
	 */
	@Primary
	@Bean(name = "masterEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean masterEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("masterDataSource") DataSource dataSource) {
		return builder.dataSource(dataSource).packages("com.example.swiggy.entity")
				.persistenceUnit("assignmentMaster").build();
	}

	/**
	 * master TransactionManager.
	 *
	 * @param entityManagerFactory
	 *            the entity manager factory
	 * @return the platform transaction manager
	 */
	@Primary
	@Bean(name = "masterPlatformTransactionManager")
	public PlatformTransactionManager masterTransactionManager(
			@Qualifier("masterEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
}