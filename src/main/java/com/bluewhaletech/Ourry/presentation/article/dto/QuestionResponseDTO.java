package com.bluewhaletech.Ourry.presentation.article.dto;

import com.fasterxml.jackson.annotation.JsonSetter;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class QuestionResponseDTO {
    @NotBlank
    @JsonSetter("questionId")
    private Long questionId;
    @NotBlank
    private int sequence;
    private String opinion;
}