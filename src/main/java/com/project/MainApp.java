package com.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class MainApp {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(MainApp.class, args);
		System.out.println("Hello World Spring Boot app started");
	}

}
