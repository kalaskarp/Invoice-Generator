package com.Invoice.Invoice.repository;

import com.Invoice.Invoice.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller,Integer> {
}
