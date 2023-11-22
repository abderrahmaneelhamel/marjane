package com.marjane.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("admin")
public class Admin extends Users {

    @OneToMany
    private List<Promotion> promotions;

    public Admin(String name, String email, String password) {
        super(name, email, password);
    }
}