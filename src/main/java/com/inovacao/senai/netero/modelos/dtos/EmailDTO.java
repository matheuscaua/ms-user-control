package com.inovacao.senai.netero.modelos.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDTO {

    @NotBlank
    private String ownerRef;
    @NotBlank
    private String mailTo;

    @NotBlank
    private String subject;

    @NotBlank
    private String text;

}
