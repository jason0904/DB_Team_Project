package com.caudbdesign.dbTeamProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync
@EnableScheduling
@SpringBootApplication

public class DbTeamProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(DbTeamProjectApplication.class, args);
	}

}
