package com.marjane.model;

import lombok.*;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Manager extends Users {

    @OneToMany
    private Set<Center> managedCenters;

    @OneToMany
    private List<PromotionApproval> promotionApprovals;

    public Manager(String name, String email, String password) {
        super(name, email, password);
    }
}
