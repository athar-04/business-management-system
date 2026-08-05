package com.athar.bms.inventory.entity;

import com.athar.bms.common.entity.BaseEntity;
import com.athar.bms.godown.entity.Godown;
import com.athar.bms.product.entity.Product;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "inventory")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Inventory extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "godown_id", nullable = false)
    private Godown godown;

    @Column(nullable = false)
    private Integer quantity;
}