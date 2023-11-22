package com.marjane.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoyaltyCard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String cardCode;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "loyaltyCard")
    private List<LoyaltyPoints> loyaltyPoints;

}