package com.inovacao.senai.netero.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "ViaCepClient")
public interface ViaCepClient {

}
