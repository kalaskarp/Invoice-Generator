package com.Invoice.Invoice.GlobalExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobaLInvoiceExceptionHandler {

    @ExceptionHandler(InvoiceNotFoundException.class)
    public ResponseEntity<?> InvoiceNotFoundExceptionHandler(InvoiceNotFoundException exception){

        String message = exception.getMessage();

        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);

    }

}
