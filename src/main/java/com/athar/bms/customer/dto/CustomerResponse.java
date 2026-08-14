package com.athar.bms.customer.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerResponse {

    private Long id;

    private String name;

    private String phone;

    private String email;

    private String address;

    private String gstNumber;

    private Boolean isActive;
}