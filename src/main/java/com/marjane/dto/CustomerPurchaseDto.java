package com.marjane.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerPurchaseDto {
    private Long id;
    private Long customerId;
    private Long cashierId;
}

