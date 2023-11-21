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
public class Admin extends User {

    @OneToMany(mappedBy = "admin")
    private List<Promotion> promotions;

    @OneToMany(mappedBy = "admin")
    private List<Center> centers;
}