package com.bluewhaletech.Ourry.domain.report.exception;

import com.bluewhaletech.Ourry.common.exception.BusinessException;
import com.bluewhaletech.Ourry.common.exception.ErrorCode;

public class ReportDetailLoadingFailedException extends BusinessException {
    public ReportDetailLoadingFailedException(String message) {
        super(ErrorCode.REPORT_DETAIL_LOADING_FAILED, message);
    }
}