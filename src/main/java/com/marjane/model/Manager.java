package com.marjane.model;

import lombok.*;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    @OneToMany(mappedBy = "manager")
    private Set<Center> managedCenters;

    public Manager(String email, String password, Set<Center> managedCenters) {
        this.email = email;
        this.password = password;
        this.managedCenters = managedCenters;
    }
}
