package com.example.bookinfomanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication  // entry point of the programm
public class BookInfoManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookInfoManagementApplication.class, args);
    }
}
