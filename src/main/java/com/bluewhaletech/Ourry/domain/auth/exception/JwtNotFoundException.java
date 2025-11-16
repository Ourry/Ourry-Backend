package com.bluewhaletech.Ourry.domain.auth.exception;

import com.bluewhaletech.Ourry.common.exception.BusinessException;
import com.bluewhaletech.Ourry.common.exception.ErrorCode;

public class JwtNotFoundException extends BusinessException {
    public JwtNotFoundException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
