package com.harmonia.HmAuthService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan({"com.harmonia.HmAuthService.controller", "com.harmonia.HmAuthService.model", "com.harmonia.HmAuthService.dao"})
@EnableJpaRepositories("com.harmonia.HmAuthService.dao")
@EntityScan("com.harmonia.HmAuthService.model.entity")
@SpringBootApplication
public class HmAuthServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HmAuthServiceApplication.class, args);
	}

}
