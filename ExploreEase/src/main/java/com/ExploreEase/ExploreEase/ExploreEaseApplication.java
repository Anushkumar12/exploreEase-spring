package com.ExploreEase.ExploreEase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class ExploreEaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExploreEaseApplication.class, args);
	}

}
