package com.categorizer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(KeywordCategoryManager.class)
public class CategorizerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CategorizerApplication.class, args);
	}

}
