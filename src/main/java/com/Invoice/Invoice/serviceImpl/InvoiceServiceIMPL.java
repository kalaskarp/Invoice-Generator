package com.Invoice.Invoice.serviceImpl;

import com.Invoice.Invoice.GlobalExceptions.InvoiceNotFoundException;
import com.Invoice.Invoice.PDFGeneratorLogic.InvoicePDFGenerator;
import com.Invoice.Invoice.entity.*;
import com.Invoice.Invoice.repository.InvoiceRepository;
import com.Invoice.Invoice.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InvoiceServiceIMPL implements InvoiceService {

    @Autowired
    InvoiceRepository invoiceRepository;

    @Override
    public Invoice invoiceGenerate(InvoiceRequest invoiceRequest) {

        Seller seller = new Seller();

        seller.setSellerName(invoiceRequest.getSeller());
        seller.setSellerAddress(invoiceRequest.getSellerAddress());
        seller.setGSTNumberSeller(invoiceRequest.getSellerGstin());

        Buyer buyer = new Buyer();
        buyer.setBuyerName(invoiceRequest.getBuyer());
        buyer.setBuyerAddress(invoiceRequest.getBuyerAddress());
        buyer.setGSTNumberBuyer(invoiceRequest.getBuyerGstin());

        Invoice invoice = new Invoice();
        invoice.setSeller(seller);
        invoice.setBuyer(buyer);

        List<Product> itemList = invoiceRequest.getItems().stream().map(dto -> {
            Product product = new Product();
            product.setItem(dto.getName());
            product.setQuantity(Integer.valueOf(dto.getQuantity()));
            product.setRate(BigDecimal.valueOf(dto.getRate()));
            product.setAmount((double) Math.multiplyHigh(Integer.valueOf(dto.getQuantity()),(int)dto.getRate()));

            product.setInvoice(invoice);

            return product;
        }).collect(Collectors.toList());

    invoice.setItems(itemList);

    InvoicePDFGenerator invoicePDFGenerator = new InvoicePDFGenerator();

    Invoice save = invoiceRepository.save(invoice);

    invoicePDFGenerator.generatePDF(invoice,invoiceRepository.findByInvoiceCount());


    return save;

    }

    @Override
    public void invoiceDownload(Long id) {
        Invoice byId = invoiceRepository.findById(id).orElseThrow(()->
                new InvoiceNotFoundException("Invoice Not Found With :"+id));

        InvoicePDFGenerator invoicePDFGenerator = new InvoicePDFGenerator();
        invoicePDFGenerator.generatePDF(byId, Math.toIntExact(id));

    }


}
