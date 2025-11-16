package com.bluewhaletech.Ourry.domain.report.exception;

import com.bluewhaletech.Ourry.common.exception.BusinessException;
import com.bluewhaletech.Ourry.common.exception.ErrorCode;

public class ReportLoadingFailedException extends BusinessException {
    public ReportLoadingFailedException(String message) {
        super(ErrorCode.REPORT_LOADING_FAILED, message);
    }
}