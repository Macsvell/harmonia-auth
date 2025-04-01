package com.harmonia.HmAuthService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.harmonia.HmAuthService.controller"})
@EntityScan("com.harmonia.HmAuthService.model.entity")
@SpringBootApplication
public class HmAuthServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HmAuthServiceApplication.class, args);
	}

}
