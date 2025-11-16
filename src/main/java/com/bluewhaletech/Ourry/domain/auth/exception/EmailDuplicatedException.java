package com.bluewhaletech.Ourry.domain.auth.exception;

import com.bluewhaletech.Ourry.common.exception.BusinessException;
import com.bluewhaletech.Ourry.common.exception.ErrorCode;

public class EmailDuplicatedException extends BusinessException {
    public EmailDuplicatedException(String message) {
        super(ErrorCode.EMAIL_DUPLICATED, message);
    }
}