package com.athar.bms.product.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequest {

    @NotBlank(message = "Product name is required")
    @Size(max = 150)
    private String name;

    @NotBlank(message = "SKU is required")
    @Size(max = 50)
    private String sku;

    @Size(max = 255)
    private String description;

    @NotNull(message = "Purchase price is required")
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal purchasePrice;

    @NotNull(message = "Selling price is required")
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal sellingPrice;

    @NotBlank(message = "Unit is required")
    private String unit;

    @NotNull(message = "Minimum stock is required")
    @Min(0)
    private Integer minimumStock;

    @NotNull(message = "Category is required")
    private Long categoryId;

    @NotNull(message = "Brand is required")
    private Long brandId;
}