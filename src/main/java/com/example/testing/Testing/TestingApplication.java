package com.example.testing.Testing;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestingApplication {
	private static final Logger logger = LogManager.getLogger(TestingApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TestingApplication.class, args);
		logger.info("Started testing application main");
	}

}
