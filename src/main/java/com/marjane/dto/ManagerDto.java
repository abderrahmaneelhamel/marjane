package com.marjane.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ManagerDto {
    private Long id;
    private String name;
    private String email;

    public ManagerDto(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    private String password;
}
