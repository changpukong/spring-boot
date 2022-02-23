package yfu.practice.springboot.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DataSourceConfig2 {

	@Bean
	@ConfigurationProperties(prefix = "student.oracle")
	public HikariConfig memoOracleProperties() {
		return new HikariConfig();
	}
	
	@Bean
	public DataSource memoOracleDataSource() {
		return new HikariDataSource(memoOracleProperties());
	}
}