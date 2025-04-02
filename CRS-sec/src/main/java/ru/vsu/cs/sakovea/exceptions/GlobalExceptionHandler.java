package ru.vsu.cs.sakovea.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
            public class GlobalExceptionHandler {

                @ExceptionHandler(CustomException.class)
                public ResponseEntity<?> handleCustomException(CustomException e) {
                    return ResponseEntity.badRequest().body(e.getMessageAsJson());
                }
            }