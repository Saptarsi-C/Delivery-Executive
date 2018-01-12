/**
 * 
 */
package com.example.swiggy.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * @author saptarsichaurashy
 *
 */
@Primary
@Component("masterDataSourceProperties")
@ConfigurationProperties(prefix = "mysql.datasource-master")
public class MasterDataSourceProperties extends DataSourceProperties {

}
