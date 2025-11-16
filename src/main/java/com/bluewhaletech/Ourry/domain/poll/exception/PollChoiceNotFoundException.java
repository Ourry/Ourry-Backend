package com.bluewhaletech.Ourry.domain.poll.exception;

import com.bluewhaletech.Ourry.common.exception.BusinessException;
import com.bluewhaletech.Ourry.common.exception.ErrorCode;

public class PollChoiceNotFoundException extends BusinessException {
    public PollChoiceNotFoundException(String message) {
        super(ErrorCode.CHOICE_NOT_FOUND, message);
    }
}
