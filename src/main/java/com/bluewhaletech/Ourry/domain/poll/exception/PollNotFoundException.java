package com.bluewhaletech.Ourry.domain.poll.exception;

import com.bluewhaletech.Ourry.common.exception.BusinessException;
import com.bluewhaletech.Ourry.common.exception.ErrorCode;

public class PollNotFoundException extends BusinessException {
    public PollNotFoundException(String message) {
        super(ErrorCode.POLL_NOT_FOUND, message);
    }
}
