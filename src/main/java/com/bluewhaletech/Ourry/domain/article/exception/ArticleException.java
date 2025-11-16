package com.bluewhaletech.Ourry.domain.article.exception;

import com.bluewhaletech.Ourry.common.exception.BusinessException;
import com.bluewhaletech.Ourry.common.exception.ErrorCode;

public abstract class ArticleException extends BusinessException {
    public ArticleException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}