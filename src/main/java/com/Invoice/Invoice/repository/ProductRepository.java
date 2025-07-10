package com.Invoice.Invoice.repository;

import com.Invoice.Invoice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}
