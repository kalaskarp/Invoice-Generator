package com.Invoice.Invoice.repository;

import com.Invoice.Invoice.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InvoiceRepository extends JpaRepository<Invoice,Long> {
   @Query("SELECT MAX(i.id) FROM Invoice i")
   int findByInvoiceCount();
}
