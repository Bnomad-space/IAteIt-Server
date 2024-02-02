package com.bnomad.IAteIt.global.error.custom;

import com.bnomad.IAteIt.global.error.BusinessException;
import com.bnomad.IAteIt.global.error.ErrorCode;

public class EntityNotFoundException extends BusinessException {
    public EntityNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
