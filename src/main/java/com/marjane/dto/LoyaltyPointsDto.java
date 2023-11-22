package com.marjane.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoyaltyPointsDto {
    private Long id;
    private int points;
    private Long LoyaltyCardId;
}
