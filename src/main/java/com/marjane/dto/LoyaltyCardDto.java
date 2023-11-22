package com.marjane.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoyaltyCardDto {
    private Long id;
    private Long customerId;
    private String cardCode;
}
