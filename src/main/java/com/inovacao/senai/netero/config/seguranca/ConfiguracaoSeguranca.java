package com.inovacao.senai.netero.config.seguranca;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class ConfiguracaoSeguranca {

    @Value("${jwksUri}")
    private String jwksUri;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests(req -> req
                        .requestMatchers("/usuario/email/{email}").permitAll()
                        .requestMatchers("/usuario/cadastrar").permitAll()
                        .requestMatchers("/empresa/cadastrar").permitAll()
                )
                .oauth2ResourceServer(
                        r -> r.jwt().jwkSetUri(jwksUri)
                );
        return http.build();
    }

}
