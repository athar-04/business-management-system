package com.athar.bms.supplierpayment.repository;

import com.athar.bms.purchase.entity.Purchase;
import com.athar.bms.supplierpayment.entity.SupplierPayment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SupplierPaymentRepository
        extends JpaRepository<SupplierPayment, Long> {

    List<SupplierPayment> findByPurchase(Purchase purchase);
}