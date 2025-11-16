package com.bluewhaletech.Ourry.presentation.member.dto;

import com.fasterxml.jackson.annotation.JsonSetter;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PasswordResetDTO {
    @NotBlank
    private String email;
    @NotBlank
    @JsonSetter("newPassword")
    private String newPassword;
    @NotBlank
    @JsonSetter("confirmPassword")
    private String confirmPassword;
}