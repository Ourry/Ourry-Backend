package com.bluewhaletech.Ourry.domain.auth.exception;

import com.bluewhaletech.Ourry.common.exception.BusinessException;
import com.bluewhaletech.Ourry.common.exception.ErrorCode;

public class EmailAuthenticationCodeMismatchException extends BusinessException {
    public EmailAuthenticationCodeMismatchException(String message) {
        super(ErrorCode.EMAIL_AUTHENTICATION_CODE_MISMATCH, message);
    }
}
