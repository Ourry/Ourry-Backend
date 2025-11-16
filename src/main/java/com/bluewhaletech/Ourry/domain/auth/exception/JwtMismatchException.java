package com.bluewhaletech.Ourry.domain.auth.exception;

import com.bluewhaletech.Ourry.common.exception.BusinessException;
import com.bluewhaletech.Ourry.common.exception.ErrorCode;

public class JwtMismatchException extends BusinessException {
    public JwtMismatchException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}