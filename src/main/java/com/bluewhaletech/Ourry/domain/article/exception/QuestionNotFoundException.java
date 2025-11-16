package com.bluewhaletech.Ourry.domain.article.exception;

import com.bluewhaletech.Ourry.common.exception.BusinessException;
import com.bluewhaletech.Ourry.common.exception.ErrorCode;

public class QuestionNotFoundException extends BusinessException {
    public QuestionNotFoundException(String message) {
        super(ErrorCode.QUESTION_NOT_FOUND, message);
    }
}
