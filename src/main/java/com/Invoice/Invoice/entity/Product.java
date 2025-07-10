package com.Invoice.Invoice.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.math.BigDecimal;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String Item;

    private Integer Quantity;

    private BigDecimal Rate;

    private Double Amount;

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;


}
