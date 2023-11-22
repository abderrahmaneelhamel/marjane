package com.marjane.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminDto {
    private Long id;
    private String name;
    private String email;
}