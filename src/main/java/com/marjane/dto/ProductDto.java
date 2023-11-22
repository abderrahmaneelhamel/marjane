package com.marjane.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
    private double price;
    private int quantity;
}
