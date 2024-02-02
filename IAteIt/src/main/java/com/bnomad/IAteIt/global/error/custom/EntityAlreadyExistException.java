package com.bnomad.IAteIt.global.error.custom;


import com.bnomad.IAteIt.global.error.BusinessException;
import com.bnomad.IAteIt.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class EntityAlreadyExistException extends BusinessException {

    public EntityAlreadyExistException(ErrorCode errorCode) {
        super(errorCode);
    }
}
