package com.example.euserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EuserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(EuserverApplication.class, args);
    }

}
