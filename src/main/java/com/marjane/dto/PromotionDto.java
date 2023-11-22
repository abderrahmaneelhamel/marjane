package com.marjane.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PromotionDto {
    private Long id;
    private String productCategory;
    private String promotionDescription;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private double discountPercentage;
    private int quantity;
    private double loyaltyPointsEarned;
    private int quantityThreshold;
}
