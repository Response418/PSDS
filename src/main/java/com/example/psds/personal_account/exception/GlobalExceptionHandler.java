package com.example.psds.personal_account.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<?> handle(ServiceException e) {
        logger.error("(Code:" + e.getCode() + ") Message: " + e.getMessage());

        return new ResponseEntity<>(e.getMessage(), HttpStatus.valueOf(e.getCode()));
    }
}
