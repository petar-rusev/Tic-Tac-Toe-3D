package com.tictac;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.leagueofsummoners.persistence.interfaces")
public class TictacApplication {

	public static void main(String[] args) {
		SpringApplication.run(TictacApplication.class, args);
	}
}
