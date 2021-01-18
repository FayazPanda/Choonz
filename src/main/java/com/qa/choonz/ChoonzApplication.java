package com.qa.choonz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class ChoonzApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ChoonzApplication.class, args);
    }

}
