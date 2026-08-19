package com.athar.bms.expense.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExpenseResponse {

    private Long id;

    private String category;

    private BigDecimal amount;

    private LocalDate expenseDate;

    private String description;

    private String paymentMethod;
}