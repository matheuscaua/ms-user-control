package com.inovacao.senai.netero.config.gateway;

import org.springframework.context.annotation.Configuration;

@Configuration
@org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient(name = "your-service-name")
public class LoadBalancerClient {
}