package com.weebindustry.weebjournal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class WeebjournalApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeebjournalApplication.class, args);
	}

}
