package com.bluewhaletech.Ourry.domain.auth.exception;

import com.bluewhaletech.Ourry.common.exception.BusinessException;
import com.bluewhaletech.Ourry.common.exception.ErrorCode;

public class EmptyJwtException extends BusinessException {
    public EmptyJwtException(String message) {
        super(ErrorCode.EMPTY_JWT, message);
    }
}