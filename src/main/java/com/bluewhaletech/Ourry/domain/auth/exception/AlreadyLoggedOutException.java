package com.bluewhaletech.Ourry.domain.auth.exception;

import com.bluewhaletech.Ourry.common.exception.BusinessException;
import com.bluewhaletech.Ourry.common.exception.ErrorCode;

public class AlreadyLoggedOutException extends BusinessException {
    public AlreadyLoggedOutException(String message) {
        super(ErrorCode.ALREADY_LOGGED_OUT, message);
    }
}