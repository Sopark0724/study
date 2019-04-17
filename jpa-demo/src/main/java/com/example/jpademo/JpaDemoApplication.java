package com.example.jpademo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class JpaDemoApplication{

    public static void main(String[] args) {
        SpringApplication.run(JpaDemoApplication.class, args);
    }
}
