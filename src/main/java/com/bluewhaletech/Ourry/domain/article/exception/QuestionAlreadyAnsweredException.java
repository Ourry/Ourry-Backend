package com.bluewhaletech.Ourry.domain.article.exception;

import com.bluewhaletech.Ourry.common.exception.BusinessException;
import com.bluewhaletech.Ourry.common.exception.ErrorCode;

public class QuestionAlreadyAnsweredException extends BusinessException {
    public QuestionAlreadyAnsweredException(String message) {
        super(ErrorCode.QUESTION_ALREADY_ANSWERED, message);
    }
}