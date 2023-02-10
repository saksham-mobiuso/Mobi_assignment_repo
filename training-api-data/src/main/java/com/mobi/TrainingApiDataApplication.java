package com.mobi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@EnableJpaRepositories(basePackages = "com.mobi.springbootstarter.topic")
@EntityScan(basePackages ="com.mobi.springbootstarter.topic")
@EnableConfigurationProperties
@ComponentScan({"com.mobi","com.mobi.springbootstarter.topic"})
public class TrainingApiDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrainingApiDataApplication.class, args);
	}

}
