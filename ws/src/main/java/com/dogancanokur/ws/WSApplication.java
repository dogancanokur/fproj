package com.dogancanokur.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class WSApplication {

    public static void main(String[] args) {
        SpringApplication.run(WSApplication.class, args);
    }

}
