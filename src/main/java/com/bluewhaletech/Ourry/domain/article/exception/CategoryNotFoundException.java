package com.bluewhaletech.Ourry.domain.article.exception;

import com.bluewhaletech.Ourry.common.exception.BusinessException;
import com.bluewhaletech.Ourry.common.exception.ErrorCode;

public class CategoryNotFoundException extends BusinessException {
    public CategoryNotFoundException(String message) {
        super(ErrorCode.CATEGORY_NOT_FOUND, message);
    }
}
