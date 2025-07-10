package com.Invoice.Invoice.entity.DTO;

import lombok.Data;

@Data
public class ItemDTO {

    private String name;
    private String quantity;
    private double rate;
    private Double amount;
}
