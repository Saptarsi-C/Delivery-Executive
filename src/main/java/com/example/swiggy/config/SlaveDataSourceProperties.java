/**
 * 
 */
package com.example.swiggy.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author saptarsichaurashy
 *
 */
@Component("slaveDataSourceProperties")
@ConfigurationProperties(prefix = "mysql.datasource-slave")
public class SlaveDataSourceProperties extends DataSourceProperties {
}
