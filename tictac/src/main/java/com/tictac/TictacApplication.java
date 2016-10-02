package com.tictac;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class TictacApplication {

	public static void main(String[] args) throws SQLException{
		SpringApplication.run(TictacApplication.class, args);
	}
}
