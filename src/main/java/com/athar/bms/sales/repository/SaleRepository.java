package com.athar.bms.sales.repository;

import com.athar.bms.customer.entity.Customer;
import com.athar.bms.sales.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    List<Sale> findByCustomer(Customer customer);
}