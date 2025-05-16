package com.whiteday.aiecolink;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AiecolinkApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiecolinkApplication.class, args);
    }

}
