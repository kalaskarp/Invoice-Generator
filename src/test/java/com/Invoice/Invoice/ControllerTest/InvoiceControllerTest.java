package com.Invoice.Invoice.ControllerTest;

import com.Invoice.Invoice.controller.InvoiceController;
import com.Invoice.Invoice.entity.Buyer;
import com.Invoice.Invoice.entity.Invoice;
import com.Invoice.Invoice.entity.InvoiceRequest;
import com.Invoice.Invoice.entity.Seller;
import com.Invoice.Invoice.service.InvoiceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.HttpStatus;

@ExtendWith(MockitoExtension.class)
public class InvoiceControllerTest {

    @Mock
    private InvoiceService invoiceService;

    @InjectMocks
    private InvoiceController invoiceController;

    private Invoice invoice;
    private InvoiceRequest invoiceRequest;
    private Buyer buyer;
    private Seller seller;

    @BeforeEach
    public void setup() {
        invoiceRequest = new InvoiceRequest();

        // Setup Buyer
            buyer = new Buyer();
            buyer.setBuyerId(1);
            buyer.setBuyerName("Test Buyer");
            buyer.setGSTNumberBuyer("29ABCDE1234F1Z5");
            buyer.setBuyerAddress("Connaught Place, Delhi");

            // Setup Seller
            seller = new Seller();
            seller.setSellerId(1);
            seller.setSellerName("Test Seller");
            seller.setGSTNumberSeller("29ABCDE5678F1Z5");
            seller.setSellerAddress("MG Road, Bangalore");

            // Setup Invoice
            invoice = new Invoice();
            invoice.setId(1L);
            invoice.setBuyer(buyer);
            invoice.setSeller(seller);

        }

    @Test
    public void testCreateInvoice_Success() {
        when(invoiceService.invoiceGenerate(invoiceRequest)).thenReturn(invoice);

        ResponseEntity<?> response = invoiceController.createInvoice(invoiceRequest);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertTrue(response.getBody() instanceof Invoice);
        assertEquals(invoice.getId(), ((Invoice) response.getBody()).getId());

        verify(invoiceService, times(1)).invoiceGenerate(invoiceRequest);
    }

    @Test
    public void testDownloadInvoice_Success() {
        Long id = 1L;

        // No return value because service returns void
        doNothing().when(invoiceService).invoiceDownload(id);

        ResponseEntity<?> response = invoiceController.DownloadInvoice(id);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Invoice Downloaded", response.getBody());

        verify(invoiceService, times(1)).invoiceDownload(id);
    }
}

