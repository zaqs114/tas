package com.webapp.tas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class TasApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(TasApplication.class, args);
    }
}
