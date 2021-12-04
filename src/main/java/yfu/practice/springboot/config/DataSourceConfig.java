package yfu.practice.springboot.config;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {

	@Bean
	@ConfigurationProperties(prefix = "student.oracle")
	public DataSourceProperties memoOracleProperties() {
		return new DataSourceProperties();
	}
	
	@Bean
	public DataSource memoOracleDataSource() {
		return memoOracleProperties().initializeDataSourceBuilder().build();
	}
}