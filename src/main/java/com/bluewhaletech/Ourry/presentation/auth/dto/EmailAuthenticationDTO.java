package com.bluewhaletech.Ourry.presentation.auth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EmailAuthenticationDTO {
    @NotBlank
    private String email;
    @NotBlank
    private String code;
}