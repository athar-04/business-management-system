package com.athar.bms.purchase.repository;

import com.athar.bms.purchase.entity.Purchase;
import com.athar.bms.supplier.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    List<Purchase> findBySupplier(Supplier supplier);
}