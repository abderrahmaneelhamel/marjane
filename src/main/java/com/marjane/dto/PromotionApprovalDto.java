package com.marjane.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PromotionApprovalDto {
    private Long id;
    private Long departmentManagerId;
    private Long promotionId;
    private int quantity;
}
