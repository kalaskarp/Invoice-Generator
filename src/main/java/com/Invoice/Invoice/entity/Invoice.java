package com.Invoice.Invoice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "seller_id")
    private Seller seller;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "buyer_id")
    private Buyer buyer;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
    private List<Product> items = new ArrayList<>();
}

