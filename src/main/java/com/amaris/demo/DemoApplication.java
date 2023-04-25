package com.amaris.demo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        System.out.println("Current Time: ============== " + LocalDateTime.now() + "============== ");
        SpringApplication.run(DemoApplication.class, args);
    }

}