package com.savelife.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class SavelifeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SavelifeApplication.class, args);
	}

}
