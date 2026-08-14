package com.athar.bms.customer.repository;

import com.athar.bms.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    boolean existsByName(String name);

    Optional<Customer> findByName(String name);
}