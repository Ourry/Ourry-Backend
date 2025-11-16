package com.bluewhaletech.Ourry.presentation.member.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class NicknameUpdateDTO {
    @NotBlank
    private String nickname;
    @NotBlank
    private String password;
}