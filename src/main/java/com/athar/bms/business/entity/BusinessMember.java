package com.athar.bms.business.entity;

import com.athar.bms.common.entity.BaseEntity;
import com.athar.bms.role.entity.Role;
import com.athar.bms.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "business_members",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"business_id", "user_id"})
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BusinessMember extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "business_id", nullable = false)
    private Business business;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @Builder.Default
    @Column(nullable = false)
    private Boolean isActive = true;
}