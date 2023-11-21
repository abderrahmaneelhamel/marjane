package com.marjane.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("customer")
public class Customer extends User {
    // Customer-specific fields and methods

    @OneToOne(mappedBy = "customer")
    private LoyaltyCard loyaltyCard;

    @OneToMany(mappedBy = "customer")
    private List<CustomerPurchase> customerPurchases;
}