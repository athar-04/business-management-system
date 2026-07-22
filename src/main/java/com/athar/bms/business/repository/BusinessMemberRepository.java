package com.athar.bms.business.repository;

import com.athar.bms.business.entity.BusinessMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessMemberRepository extends JpaRepository<BusinessMember, Long> {
}