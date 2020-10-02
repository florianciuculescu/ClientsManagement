package com.example.clientsmanagement.advice;

import com.example.clientsmanagement.exception.BadRequestException;
import com.example.clientsmanagement.exception.DatabaseViolationExeption;
import com.example.clientsmanagement.model.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ErrorControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {BadRequestException.class})
    public ResponseEntity<ErrorDTO> handleBadRequestException(BadRequestException badRequestException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorDTO.builder()
                        .message(badRequestException.getMessage())
                        .build());
    }

    @ExceptionHandler(value = {DatabaseViolationExeption.class})
    public ResponseEntity<ErrorDTO> handleDataBaseViolationException(DatabaseViolationExeption databaseViolationExeption) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorDTO.builder()
                        .message(databaseViolationExeption.getMessage())
                        .build());
    }
}
