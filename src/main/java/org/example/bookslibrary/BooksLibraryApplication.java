package org.example.bookslibrary;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
@OpenAPIDefinition(servers =
		{
				@Server(
						url = "http://localhost:5920",
						description = "Local Project for Book Library Management System"
				)
		}
)
public class BooksLibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(BooksLibraryApplication.class, args);
	}

}
