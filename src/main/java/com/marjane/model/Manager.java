package com.marjane.model;

import lombok.*;

import jakarta.persistence.*;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@Builder
public class Manager extends User {

    @OneToMany(mappedBy = "manager")
    private Set<Center> managedCenters;

    public Manager(Set<Center> managedCenters) {
        super();
        this.managedCenters = managedCenters;
    }
}
