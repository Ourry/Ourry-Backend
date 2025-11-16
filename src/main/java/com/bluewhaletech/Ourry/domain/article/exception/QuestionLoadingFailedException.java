package com.bluewhaletech.Ourry.domain.article.exception;

import com.bluewhaletech.Ourry.common.exception.BusinessException;
import com.bluewhaletech.Ourry.common.exception.ErrorCode;

public class QuestionLoadingFailedException extends BusinessException {
    public QuestionLoadingFailedException(String message) {
        super(ErrorCode.QUESTION_LOADING_FAILED, message);
    }
}