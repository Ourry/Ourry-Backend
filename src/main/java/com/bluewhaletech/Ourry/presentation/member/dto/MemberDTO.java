package com.bluewhaletech.Ourry.presentation.member.dto;

import com.fasterxml.jackson.annotation.JsonSetter;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberDTO {
    @NotBlank
    @JsonSetter("memberId")
    private Long memberId;
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String nickname;
    @NotBlank
    private String phone;
}