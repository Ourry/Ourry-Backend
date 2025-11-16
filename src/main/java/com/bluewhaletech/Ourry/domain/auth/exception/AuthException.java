package com.bluewhaletech.Ourry.domain.auth.exception;

import com.bluewhaletech.Ourry.common.exception.BusinessException;
import com.bluewhaletech.Ourry.common.exception.ErrorCode;

public abstract class AuthException extends BusinessException {
    public AuthException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
