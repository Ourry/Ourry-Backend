package com.bluewhaletech.Ourry.domain.report.exception;

import com.bluewhaletech.Ourry.common.exception.BusinessException;
import com.bluewhaletech.Ourry.common.exception.ErrorCode;

public class ReportHistoryExistException extends BusinessException {
    public ReportHistoryExistException(String message) {
        super(ErrorCode.REPORT_HISTORY_EXIST, message);
    }
}