package com.cancer.security.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value = "com.cancer")
public class CancerSecurityDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CancerSecurityDemoApplication.class, args);
    }
}
