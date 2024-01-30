package com.example.psds.personal_account.exception;

import lombok.Getter;

@Getter
public class ServiceException extends Exception {
    private final Integer code;
    public ServiceException(CustomErrorMessage e) {
        super(e.getMessage());
        this.code = e.getCode();
    }
}