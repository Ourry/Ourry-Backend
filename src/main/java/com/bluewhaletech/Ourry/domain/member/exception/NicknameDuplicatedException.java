package com.bluewhaletech.Ourry.domain.member.exception;

import com.bluewhaletech.Ourry.common.exception.BusinessException;
import com.bluewhaletech.Ourry.common.exception.ErrorCode;

public class NicknameDuplicatedException extends BusinessException {
    public NicknameDuplicatedException(String message) {
        super(ErrorCode.NICKNAME_DUPLICATED, message);
    }
}