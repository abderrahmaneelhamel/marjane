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
    @JoinColumn(name = "admin_id")
    private Admin admin;

    public LoyaltyPoints(int points, Admin admin) {
        this.points = points;
        this.admin = admin;
    }
}
