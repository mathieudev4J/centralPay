package com.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.spring.entity.Urls;
import com.spring.repositories.RepositoryUrls;

@SpringBootApplication
public class ProjectSpring2Application {
	RepositoryUrls repositoryUrls;
	public static void main(String[] args) {
		SpringApplication.run(ProjectSpring2Application.class, args);
	}

	
}
