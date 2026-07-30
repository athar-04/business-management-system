package com.athar.bms.brand.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BrandResponse {

    private Long id;

    private String name;

    private String description;

    private Boolean isActive;

    private Long categoryId;

    private String categoryName;
}