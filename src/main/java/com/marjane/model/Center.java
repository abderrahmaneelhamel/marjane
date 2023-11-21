package com.marjane.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "centers")
public class Center {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String cityName;

    @OneToMany(mappedBy = "marjaneCenter")
    private List<Manager> departmentManagers;

    @OneToMany(mappedBy = "marjaneCenter")
    private List<Cashier> cashiers;

}
