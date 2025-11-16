package com.bluewhaletech.Ourry.domain.report.exception;

import com.bluewhaletech.Ourry.common.exception.BusinessException;
import com.bluewhaletech.Ourry.common.exception.ErrorCode;

public abstract class ReportException extends BusinessException {
    public ReportException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}