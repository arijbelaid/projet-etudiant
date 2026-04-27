package com.example.api_etudiant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
//@EnableCaching
public class ApiEtudiantApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiEtudiantApplication.class, args);
	}

}