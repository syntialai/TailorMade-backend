package com.future.tailormade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableWebFlux
@SpringBootApplication
public class TailormadeApplication {

    public static void main(String[] args) {
        SpringApplication.run(TailormadeApplication.class, args);
    }
}
