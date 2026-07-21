package com.athar.bms.business.entity;

import com.athar.bms.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "businesses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Business extends BaseEntity {

    @Column(nullable = false, length = 150)
    private String businessName;

    @Column(length = 30, unique = true)
    private String gstNumber;

    @Column(length = 20)
    private String phone;

    @Column(length = 150)
    private String email;

    @Column(columnDefinition = "TEXT")
    private String address;

    @Column(length = 500)
    private String logo;

    @Default
    @Column(nullable = false)
    private Boolean isActive = true;
}