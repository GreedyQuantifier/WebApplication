package com.example.serviceFakeNews;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;


@SpringBootApplication
@PropertySource("classpath:database.properties")
public class ServiceFakeNewsApplication {
	public static void main(String[] args) {
		SpringApplication.run(ServiceFakeNewsApplication.class, args);
	}

}
