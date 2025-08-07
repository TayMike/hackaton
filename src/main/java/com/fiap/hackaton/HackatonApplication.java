package com.fiap.hackaton;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class HackatonApplication {

	public static void main(String[] args) {
		SpringApplication.run(HackatonApplication.class, args);
	}

}
