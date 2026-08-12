package com.athar.bms.supplier.entity;

import com.athar.bms.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "suppliers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Supplier extends BaseEntity {

    @Column(nullable = false, length = 150)
    private String name;

    @Column(length = 20)
    private String phone;

    @Column(length = 150)
    private String email;

    @Column(length = 255)
    private String address;

    @Column(length = 20)
    private String gstNumber;

    @Builder.Default
    @Column(nullable = false)
    private Boolean isActive = true;
}