package com.hella.ICTManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class IctManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(IctManagerApplication.class, args);
	}

}
