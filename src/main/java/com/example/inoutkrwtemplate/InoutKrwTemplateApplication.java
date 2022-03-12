package com.example.inoutkrwtemplate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class InoutKrwTemplateApplication {

    public static void main(String[] args) {
        SpringApplication.run(InoutKrwTemplateApplication.class, args);
    }

}
