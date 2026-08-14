package com.athar.bms.sales.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleRequest {

    @NotNull(message = "Customer ID is required")
    private Long customerId;

    @NotNull(message = "Godown ID is required")
    private Long godownId;

    @NotNull(message = "Sale date is required")
    private LocalDate saleDate;

    @NotEmpty(message = "At least one sale item is required")
    @Valid
    private List<SaleItemRequest> items;
}