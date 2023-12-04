package com.marjane.model;

import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productCategory;
    private String promotionDescription;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(name = "discount_percentage")
    private double discountPercentage;

    private int quantity;

    private double loyaltyPointsEarned;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToMany(mappedBy = "promotion")
    private List<PromotionApproval> promotionApprovals;

    public Promotion(String productCategory, String promotionDescription, LocalDateTime startTime, LocalDateTime endTime, double discountPercentage, int quantity, double loyaltyPointsEarned, Product product) {
        this.productCategory = productCategory;
        this.promotionDescription = promotionDescription;
        this.startTime = startTime;
        this.endTime = endTime;
        this.discountPercentage = discountPercentage;
        this.quantity = quantity;
        this.loyaltyPointsEarned = loyaltyPointsEarned;
        this.product = product;
    }
}
