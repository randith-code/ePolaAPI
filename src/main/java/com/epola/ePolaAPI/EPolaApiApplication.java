package com.epola.ePolaAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
@SpringBootApplication
public class EPolaApiApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(EPolaApiApplication.class);
		SpringApplication.run(EPolaApiApplication.class, args);
	}

}
