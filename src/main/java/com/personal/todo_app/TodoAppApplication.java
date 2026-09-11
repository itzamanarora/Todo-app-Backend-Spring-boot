package com.personal.todo_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class TodoAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoAppApplication.class, args);
	}

}
