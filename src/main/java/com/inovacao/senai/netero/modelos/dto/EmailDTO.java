package com.inovacao.senai.netero.modelos.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class EmailDTO {

    @NotBlank
    private String ownerRef;

    @NotBlank
    @Email
    private String mailFrom;

    @NotBlank
    private String mailTo;

    @NotBlank
    private String subject;

    @NotBlank
    private String text;

}
