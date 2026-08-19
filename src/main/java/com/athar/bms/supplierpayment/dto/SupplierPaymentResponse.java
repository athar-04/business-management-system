package com.athar.bms.supplierpayment.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SupplierPaymentResponse {

    private Long id;

    private Long purchaseId;

    private Long supplierId;

    private String supplierName;

    private BigDecimal purchaseAmount;

    private BigDecimal amount;

    private LocalDate paymentDate;

    private String paymentMethod;

    private String notes;
}