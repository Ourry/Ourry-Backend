package com.bluewhaletech.Ourry.domain.poll.exception;

import com.bluewhaletech.Ourry.common.exception.BusinessException;
import com.bluewhaletech.Ourry.common.exception.ErrorCode;

public class SolutionNotFoundException extends BusinessException {
    public SolutionNotFoundException(String message) {
        super(ErrorCode.SOLUTION_NOT_FOUND, message);
    }
}