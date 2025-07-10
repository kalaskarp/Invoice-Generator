package com.Invoice.Invoice.GlobalExceptions;

public class InvoiceNotFoundException extends RuntimeException {

    public InvoiceNotFoundException(String Message){
        super(Message);
    }
}
