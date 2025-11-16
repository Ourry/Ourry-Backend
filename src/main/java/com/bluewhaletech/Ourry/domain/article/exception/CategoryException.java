package com.bluewhaletech.Ourry.domain.article.exception;

import com.bluewhaletech.Ourry.common.exception.BusinessException;
import com.bluewhaletech.Ourry.common.exception.ErrorCode;

public abstract class CategoryException extends BusinessException {
    public CategoryException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
