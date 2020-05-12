package com.flightticketbooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class FlightticketbookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlightticketbookingApplication.class, args);
	}

}
