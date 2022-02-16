package com.tistory.workshop6349.workshoptodo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class WorkshopTodoApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkshopTodoApplication.class, args);
    }

}
