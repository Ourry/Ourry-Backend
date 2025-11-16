package com.bluewhaletech.Ourry.domain.poll.exception;

import com.bluewhaletech.Ourry.common.exception.BusinessException;
import com.bluewhaletech.Ourry.common.exception.ErrorCode;

public class AnswerToOneselfException extends BusinessException {
    public AnswerToOneselfException(String message) {
        super(ErrorCode.ANSWER_TO_ONESELF, message);
    }
}
