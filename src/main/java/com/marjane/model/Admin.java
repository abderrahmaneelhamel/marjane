package com.marjane.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;

    @OneToMany(mappedBy = "admin")
    private List<Promotion> promotions;

    @OneToMany(mappedBy = "admin")
    private List<Center> centers;

    @OneToMany(mappedBy = "admin")
    private List<LoyaltyPoints> loyaltyPoints;

    public Admin(String email, String password) {
        this.email = email;
        this.password = password;
    }
}