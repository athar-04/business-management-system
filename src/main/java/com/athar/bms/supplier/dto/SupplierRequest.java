package com.athar.bms.supplier.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SupplierRequest {

    @NotBlank(message = "Supplier name is required")
    @Size(max = 150)
    private String name;

    @Size(max = 20)
    private String phone;

    @Email(message = "Invalid email format")
    @Size(max = 150)
    private String email;

    @Size(max = 255)
    private String address;

    @Size(max = 20)
    private String gstNumber;
}