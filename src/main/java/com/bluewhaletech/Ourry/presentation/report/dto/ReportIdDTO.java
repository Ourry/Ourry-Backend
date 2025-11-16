package com.bluewhaletech.Ourry.presentation.report.dto;

import com.fasterxml.jackson.annotation.JsonSetter;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ReportIdDTO {
    @NotBlank
    @JsonSetter("reportId")
    private Long reportId;
}