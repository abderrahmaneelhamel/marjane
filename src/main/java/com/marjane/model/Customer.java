package com.marjane.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("customer")
public class Customer extends Users {

    @OneToOne(mappedBy = "customer")
    private LoyaltyCard loyaltyCard;

    @OneToMany(mappedBy = "customer")
    private List<CustomerPurchase> customerPurchases;

    public Customer(String name, String email, String password) {
        super(name, email, password);
    }
}