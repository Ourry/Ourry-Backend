package com.bluewhaletech.Ourry.domain.member.exception;

import com.bluewhaletech.Ourry.common.exception.BusinessException;
import com.bluewhaletech.Ourry.common.exception.ErrorCode;

public abstract class MemberException extends BusinessException {
    public MemberException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}