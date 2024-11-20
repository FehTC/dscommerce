package com.devsuperior.dscommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.devsuperior.Entitie")
@ComponentScan(basePackages = {"com.devsuperior.Controllers"})
@EnableJpaRepositories(basePackages = "com.devsuperior.Repositories")
@ComponentScan(basePackages = {"com.devsuperior.Services"})
public class DscommerceApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(DscommerceApplication.class, args);
	}


}
