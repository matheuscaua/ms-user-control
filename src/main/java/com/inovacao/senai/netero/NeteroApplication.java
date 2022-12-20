package com.inovacao.senai.netero;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class NeteroApplication {

    public static void main(String[] args) {
        SpringApplication.run(NeteroApplication.class, args);
    }

}
