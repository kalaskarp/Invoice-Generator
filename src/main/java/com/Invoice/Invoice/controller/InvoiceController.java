package com.Invoice.Invoice.controller;

import com.Invoice.Invoice.entity.Invoice;
import com.Invoice.Invoice.entity.InvoiceRequest;
import com.Invoice.Invoice.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/invoice")
public class InvoiceController {

    @Autowired
    InvoiceService service;

    @PostMapping("/GenerateInvoice")
    ResponseEntity<?> createInvoice(@RequestBody InvoiceRequest request){
        Invoice invoice = service.invoiceGenerate(request);

        return new ResponseEntity<>(invoice, HttpStatus.CREATED);
    }

    @GetMapping("/id/{id}")
    ResponseEntity<?> DownloadInvoice(@PathVariable Long id){
        service.invoiceDownload(id);

        return new ResponseEntity<>("Invoice Downloaded", HttpStatus.CREATED);
    }

}
