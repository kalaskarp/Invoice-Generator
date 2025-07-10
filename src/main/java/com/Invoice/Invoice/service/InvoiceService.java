package com.Invoice.Invoice.service;

import com.Invoice.Invoice.entity.Invoice;
import com.Invoice.Invoice.entity.InvoiceRequest;
import org.springframework.stereotype.Service;

@Service
public interface InvoiceService {

    Invoice invoiceGenerate(InvoiceRequest invoiceRequest);

    void invoiceDownload(Long Id);
}
