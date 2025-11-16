package com.bluewhaletech.Ourry.domain.member.exception;

import com.bluewhaletech.Ourry.common.exception.BusinessException;
import com.bluewhaletech.Ourry.common.exception.ErrorCode;

public class MemberNotFoundException extends BusinessException {
    public MemberNotFoundException(String message) {
        super(ErrorCode.MEMBER_NOT_FOUND, message);
    }
}