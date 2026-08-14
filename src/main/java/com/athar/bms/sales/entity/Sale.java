package com.athar.bms.sales.entity;

import com.athar.bms.customer.entity.Customer;
import com.athar.bms.godown.entity.Godown;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sales")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "godown_id", nullable = false)
    private Godown godown;

    @Column(nullable = false)
    private LocalDate saleDate;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal totalAmount;

    @OneToMany(
            mappedBy = "sale",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @Builder.Default
    private List<SaleItem> items = new ArrayList<>();
}