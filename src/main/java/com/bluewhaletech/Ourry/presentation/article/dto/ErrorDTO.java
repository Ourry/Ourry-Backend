package com.bluewhaletech.Ourry.presentation.article.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorDTO {
    @NotBlank
    private String code;
    @NotBlank
    private String message;
    @NotBlank
    private int status;
}