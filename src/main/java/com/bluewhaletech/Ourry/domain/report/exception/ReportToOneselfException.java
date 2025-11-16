package com.bluewhaletech.Ourry.domain.report.exception;

import com.bluewhaletech.Ourry.common.exception.BusinessException;
import com.bluewhaletech.Ourry.common.exception.ErrorCode;

public class ReportToOneselfException extends BusinessException {
    public ReportToOneselfException(String message) {
        super(ErrorCode.REPORT_TO_ONESELF, message);
    }
}