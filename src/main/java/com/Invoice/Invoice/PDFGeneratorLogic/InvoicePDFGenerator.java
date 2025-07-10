package com.Invoice.Invoice.PDFGeneratorLogic;

import com.Invoice.Invoice.entity.*;
import com.Invoice.Invoice.repository.InvoiceRepository;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class InvoicePDFGenerator {



    public void generatePDF(Invoice invoice,int count) {
        try {
            Document document = new Document();


            PdfWriter.getInstance(document, new FileOutputStream("invoice"+ count +".pdf"));
            document.open();

            Font boldFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
            Font normalFont = new Font(Font.FontFamily.COURIER, 10);

            Paragraph invoiceNumberPara = new Paragraph("Invoice No: " + count, boldFont);
            invoiceNumberPara.setSpacingAfter(10f);
            invoiceNumberPara.setIndentationRight(10f);
            invoiceNumberPara.setAlignment(Element.ALIGN_RIGHT);
            document.add(invoiceNumberPara);

            // Seller and Buyer Table
            PdfPTable headerTable = new PdfPTable(2);
            headerTable.setWidthPercentage(100);
            headerTable.setWidths(new int[]{1, 1});

            PdfPCell sellerCell = new PdfPCell();
            sellerCell.setBorder(Rectangle.BOX);
            sellerCell.addElement(new Paragraph("Seller:\n"+invoice.getSeller().getSellerName()+"\n"+invoice.getSeller().getSellerAddress()+"\nGSTIN: "+invoice.getSeller().getGSTNumberSeller(), normalFont));

            PdfPCell buyerCell = new PdfPCell();
            buyerCell.setBorder(Rectangle.BOX);
            buyerCell.addElement(new Paragraph("Buyer:\n"+invoice.getBuyer().getBuyerName()+"\n"+invoice.getBuyer().getBuyerAddress()+"\nGSTIN: "+invoice.getBuyer().getGSTNumberBuyer(), normalFont));

            headerTable.addCell(sellerCell);
            headerTable.addCell(buyerCell);
            document.add(headerTable);
            document.add(Chunk.NEWLINE);

            // Items Table
            PdfPTable itemTable = new PdfPTable(4);
            itemTable.setWidthPercentage(100);
            itemTable.setWidths(new int[]{4, 2, 2, 2});

            itemTable.addCell(createHeaderCell("Item"));
            itemTable.addCell(createHeaderCell("Quantity"));
            itemTable.addCell(createHeaderCell("Rate"));
            itemTable.addCell(createHeaderCell("Amount"));

            // Sample product list

            List<Product> products = new ArrayList<>(invoice.getItems());

            BigDecimal grandTotal = BigDecimal.ZERO;

            for (Product p : products) {
                BigDecimal amount = p.getRate().multiply(BigDecimal.valueOf(p.getQuantity()));
                grandTotal = grandTotal.add(amount);

                itemTable.addCell(createBodyCell(p.getItem()));
                itemTable.addCell(createBodyCell(p.getQuantity()+" Nos"));
                itemTable.addCell(createBodyCell(p.getRate().toString()));
                itemTable.addCell(createBodyCell(amount.toString()));
            }

            // Total Row
            itemTable.addCell(createBodyCell(""));
            itemTable.addCell(createBodyCell(""));
            itemTable.addCell(createHeaderCell("Total"));
            itemTable.addCell(createHeaderCell(grandTotal.toString()));

            document.add(itemTable);
            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static PdfPCell createHeaderCell(String text) {
        Font font = new Font(Font.FontFamily.HELVETICA, 11, Font.BOLD);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setFixedHeight(22f);
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        return cell;
    }

    private static PdfPCell createBodyCell(String text) {
        Font font = new Font(Font.FontFamily.COURIER, 10);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setFixedHeight(20f);
        return cell;
    }

}
