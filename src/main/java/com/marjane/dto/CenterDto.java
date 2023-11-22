package com.marjane.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CenterDto {
    private Long id;

    public CenterDto(String cityName) {
        this.cityName = cityName;
    }

    private String cityName;
}
