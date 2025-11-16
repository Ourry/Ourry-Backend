package com.bluewhaletech.Ourry.domain.auth.exception;

import com.bluewhaletech.Ourry.common.exception.BusinessException;
import com.bluewhaletech.Ourry.common.exception.ErrorCode;

public class PasswordConfirmException extends BusinessException {
    public PasswordConfirmException(String message) {
        super(ErrorCode.PASSWORD_MISMATCH, message);
    }
}