package com.athar.bms.business.repository;

import com.athar.bms.business.entity.Business;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessRepository extends JpaRepository<Business, Long> {

    boolean existsByGstNumber(String gstNumber);

}