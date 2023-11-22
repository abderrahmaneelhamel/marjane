package com.marjane.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("cashier")
public class Cashier extends Users {

    @ManyToOne
    @JoinColumn(name = "center_id")
    private Center center;

    @OneToMany(mappedBy = "cashier")
    private List<CustomerPurchase> customerPurchases;

    public Cashier(String name, String email, String password) {
        super(name, email, password);
    }
}