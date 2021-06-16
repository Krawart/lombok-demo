package com.krawart.lombok.shared.presentation.v1.controllers;

import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@NoArgsConstructor
public class ExceptionHandlerController {

    @ExceptionHandler
    protected ResponseEntity<String> handleGeneralError(RuntimeException ex) {
        String bodyOfResponse = "Oops. Some error occured. Please contact our support";
        return ResponseEntity.internalServerError().body(bodyOfResponse);
    }
}
