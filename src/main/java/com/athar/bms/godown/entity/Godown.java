package com.athar.bms.godown.entity;

import com.athar.bms.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "godowns")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Godown extends BaseEntity {

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 255)
    private String location;

    @Column(length = 255)
    private String description;

    @Builder.Default
    @Column(nullable = false)
    private Boolean isActive = true;
}