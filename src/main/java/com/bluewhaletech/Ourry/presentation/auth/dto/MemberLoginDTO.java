package com.bluewhaletech.Ourry.presentation.auth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class MemberLoginDTO {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
