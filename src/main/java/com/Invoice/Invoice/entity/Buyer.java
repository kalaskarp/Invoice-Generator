package com.Invoice.Invoice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Buyer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer buyerId;

    private String buyerName;

    private String GSTNumberBuyer;

    private String BuyerAddress;


}
