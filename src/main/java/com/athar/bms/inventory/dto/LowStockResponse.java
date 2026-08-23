package com.athar.bms.inventory.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LowStockResponse {

    private Long inventoryId;

    private Long productId;
    private String productName;

    private Long godownId;
    private String godownName;

    private Integer currentQuantity;

    private Integer threshold;
}