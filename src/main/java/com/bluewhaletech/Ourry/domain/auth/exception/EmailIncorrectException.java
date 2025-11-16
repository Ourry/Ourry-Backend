package com.bluewhaletech.Ourry.domain.auth.exception;

import com.bluewhaletech.Ourry.common.exception.BusinessException;
import com.bluewhaletech.Ourry.common.exception.ErrorCode;

public class EmailIncorrectException extends BusinessException {
    public EmailIncorrectException(String message) {
        super(ErrorCode.EMAIL_INCORRECT, message);
    }
}
