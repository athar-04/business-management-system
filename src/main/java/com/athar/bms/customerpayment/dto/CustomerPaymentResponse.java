package com.athar.bms.customerpayment.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerPaymentResponse {

    private Long id;

    private Long saleId;

    private Long customerId;

    private String customerName;

    private BigDecimal saleAmount;

    private BigDecimal amount;

    private LocalDate paymentDate;

    private String paymentMethod;

    private String notes;
}