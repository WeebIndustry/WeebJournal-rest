package com.weebindustry.weebjournal;

import com.weebindustry.weebjournal.config.SwaggerConfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EnableFeignClients
@Import(SwaggerConfig.class)
public class WeebjournalApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeebjournalApplication.class, args);
	}

}
