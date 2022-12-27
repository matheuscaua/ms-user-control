package com.inovacao.senai.netero.configuracao;

import feign.Logger;
import feign.okhttp.OkHttpClient;
import lombok.AllArgsConstructor;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@AllArgsConstructor
@EnableFeignClients()
public class ViaCepClientConfiguration {

    @Bean
    public OkHttpClient client() {
        return new OkHttpClient();
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
