package com.bluewhaletech.Ourry.presentation.member.dto;

import com.fasterxml.jackson.annotation.JsonSetter;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class MemberIdDTO {
    @NotBlank
    @JsonSetter("memberId")
    private Long memberId;
}
