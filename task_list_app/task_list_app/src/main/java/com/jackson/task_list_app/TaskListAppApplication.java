package com.jackson.task_list_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.jackson.task_list_app.api.controller", "com.jackson.task_list_app.api.services"})
@EnableJpaRepositories(basePackages = "com.jackson.task_list_app.api.repository")
@EntityScan(basePackages = "com.jackson.task_list_app.api.models")
public class TaskListAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskListAppApplication.class, args);
	}

}
