package com.krawart.lombok.shared.presentation.v1.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler
    protected ResponseEntity<String> handleGeneralError(RuntimeException ex) {
        log.error("Some unhandled error occurred. " +
                "message: " + ex.getMessage() +
                ", stack: " + Arrays.toString(ex.getStackTrace()));

        String bodyOfResponse = "Oops. Some error occurred. Please contact our support";
        return ResponseEntity.internalServerError().body(bodyOfResponse);
    }
}
