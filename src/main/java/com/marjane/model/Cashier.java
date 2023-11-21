package com.marjane.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("cashier")
public class Cashier extends User {
    // Cashier-specific fields and methods

    @ManyToOne
    @JoinColumn(name = "center_id")
    private Center marjaneCenter;

    @OneToMany(mappedBy = "cashier")
    private List<CustomerPurchase> customerPurchases;
}