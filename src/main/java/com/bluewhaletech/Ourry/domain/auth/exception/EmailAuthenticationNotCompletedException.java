package com.bluewhaletech.Ourry.domain.auth.exception;

import com.bluewhaletech.Ourry.common.exception.BusinessException;
import com.bluewhaletech.Ourry.common.exception.ErrorCode;

public class EmailAuthenticationNotCompletedException extends BusinessException {
    public EmailAuthenticationNotCompletedException(String message) {
        super(ErrorCode.EMAIL_AUTHENTICATION_NOT_COMPLETED, message);
    }
}
