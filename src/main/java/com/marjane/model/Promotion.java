package com.marjane.model;

import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
    private int quantityThreshold;

    @ManyToOne
    @JoinColumn(name = "center_id")
    private Center center;

    @ManyToOne
    @JoinColumn(name = "multimedia_product_id")
    private Product product;

    public Promotion(String productCategory, String promotionDescription, LocalDateTime startTime, LocalDateTime endTime, double discountPercentage, int quantity, double loyaltyPointsEarned, int quantityThreshold, Center center, Product product) {
        this.productCategory = productCategory;
        this.promotionDescription = promotionDescription;
        this.startTime = startTime;
        this.endTime = endTime;
        this.discountPercentage = discountPercentage;
        this.quantity = quantity;
        this.loyaltyPointsEarned = loyaltyPointsEarned;
        this.quantityThreshold = quantityThreshold;
        this.center = center;
        this.product = product;
    }
}
