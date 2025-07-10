package com.Invoice.Invoice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sellerId;

    private String sellerName;

    private String SellerAddress;

    public String getSellerAddress() {
        return SellerAddress;
    }

    public void setSellerAddress(String sellerAddress) {
        SellerAddress = sellerAddress;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getGSTNumberSeller() {
        return GSTNumberSeller;
    }

    public void setGSTNumberSeller(String GSTNumberSeller) {
        this.GSTNumberSeller = GSTNumberSeller;
    }

    private String GSTNumberSeller;
}
