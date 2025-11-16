package com.bluewhaletech.Ourry.presentation.member.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EmailDTO {
    @NotBlank
    private String email;
    @NotBlank
    private String title;
    @NotBlank
    private String text;
}
