package com.marjane.model;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PromotionApproval {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "department_manager_id")
    private Manager departmentManager;

    @ManyToOne
    @JoinColumn(name = "promotion_id")
    private Promotion promotion;

    private int quantity;

}
