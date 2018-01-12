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
@EnableJpaRepositories(entityManagerFactoryRef = "slaveEntityManagerFactory", transactionManagerRef = "slavePlatformTransactionManager", basePackages = {
		"com.example.swiggy.repo" })
public class SlaveDbConfig {

	/**
	 * Slave data source.
	 *
	 * @param properties
	 *            the properties
	 * @return the data source
	 */
	@Bean(name = "slaveDataSource")
	public DataSource slaveDataSource(@Qualifier("slaveDataSourceProperties") DataSourceProperties properties) {
		return properties.initializeDataSourceBuilder().type(properties.getType()).build();
	}

	/**
	 * Slave entity manager factory.
	 *
	 * @param builder
	 *            the builder
	 * @param dataSource
	 *            the data source
	 * @return the local container entity manager factory bean @return.
	 */
	@Bean(name = "slaveEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean slaveEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("slaveDataSource") DataSource dataSource) {
		return builder.dataSource(dataSource).packages("com.example.swiggy.entity")
				.persistenceUnit("assignmentSlave").build();

	}

	/**
	 * slave TransactionManager.
	 *
	 * @param entityManagerFactory
	 *            the entity manager factory
	 * @return the platform transaction manager
	 */
	@Bean(name = "slavePlatformTransactionManager")
	public PlatformTransactionManager slaveTransactionManager(
			@Qualifier("slaveEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
}