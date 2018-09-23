package com.codeboyq.daybook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainApp {

    public static final String REST_URL_BASE = "daybook";

    public static void main(String[] args) {
        SpringApplication.run(MainApp.class, args);
    }

}
