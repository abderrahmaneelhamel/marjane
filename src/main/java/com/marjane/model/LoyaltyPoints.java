package com.marjane.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "loyalty_points")
public class LoyaltyPoints {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int points;

    @ManyToOne
    @JoinColumn(name = "loyalty_card_id")
    private LoyaltyCard loyaltyCard;

    public LoyaltyPoints(int points) {
        this.points = points;
    }
}
