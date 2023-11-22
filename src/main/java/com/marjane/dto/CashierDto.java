package com.marjane.dto;

import com.marjane.model.Center;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CashierDto {
    private Long id;
    private String name;
    private String email;
    private Center center;
}
