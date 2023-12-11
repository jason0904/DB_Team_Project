package com.caudbdesign.dbTeamProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication

public class DbTeamProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(DbTeamProjectApplication.class, args);
	}

}
