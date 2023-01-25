package com.inovacao.senai.netero;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;

@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
@ImportAutoConfiguration({FeignAutoConfiguration.class})
public class NeteroApplication {

    public static void main(String[] args) {
        SpringApplication.run(NeteroApplication.class, args);
    }

}
