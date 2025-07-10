package com.Invoice.Invoice.repository;

import com.Invoice.Invoice.entity.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuyerRepository  extends JpaRepository<Buyer, Long> {
}
