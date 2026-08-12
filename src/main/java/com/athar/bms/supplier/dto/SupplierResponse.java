package com.athar.bms.supplier.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SupplierResponse {

    private Long id;

    private String name;

    private String phone;

    private String email;

    private String address;

    private String gstNumber;

    private Boolean isActive;
}