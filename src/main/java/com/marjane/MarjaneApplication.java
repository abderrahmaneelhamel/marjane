package com.marjane;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import javax.sql.DataSource;

@SpringBootApplication
public class MarjaneApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarjaneApplication.class, args);
	}

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/marjane");
		dataSource.setUsername("postgres");
		dataSource.setPassword("admin");
		return dataSource;
	}

}