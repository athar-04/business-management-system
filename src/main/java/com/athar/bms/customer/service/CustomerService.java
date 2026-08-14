package com.athar.bms.customer.service;

import com.athar.bms.customer.dto.CustomerRequest;
import com.athar.bms.customer.dto.CustomerResponse;

import java.util.List;

public interface CustomerService {

    CustomerResponse createCustomer(CustomerRequest request);

    List<CustomerResponse> getAllCustomers();

    CustomerResponse getCustomerById(Long id);

    CustomerResponse updateCustomer(Long id, CustomerRequest request);

    void deleteCustomer(Long id);
}