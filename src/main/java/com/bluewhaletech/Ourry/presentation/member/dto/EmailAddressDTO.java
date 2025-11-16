package com.bluewhaletech.Ourry.presentation.member.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class EmailAddressDTO {
    @NotBlank
    private String email;
}
