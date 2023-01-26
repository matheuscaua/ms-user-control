package com.inovacao.senai.netero.modelos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.net.URI;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ViaCepUriDTO {

    private URI uri;

}
