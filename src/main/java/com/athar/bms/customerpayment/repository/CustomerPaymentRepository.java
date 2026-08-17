package com.athar.bms.customerpayment.repository;

import com.athar.bms.customerpayment.entity.CustomerPayment;
import com.athar.bms.sales.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerPaymentRepository
        extends JpaRepository<CustomerPayment, Long> {

    List<CustomerPayment> findBySale(Sale sale);
}