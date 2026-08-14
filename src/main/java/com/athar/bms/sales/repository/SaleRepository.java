package com.athar.bms.sales.repository;

import com.athar.bms.sales.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long> {
}