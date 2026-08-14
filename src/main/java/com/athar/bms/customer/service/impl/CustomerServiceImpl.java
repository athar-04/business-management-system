package com.athar.bms.customer.service.impl;

import com.athar.bms.customer.dto.CustomerRequest;
import com.athar.bms.customer.dto.CustomerResponse;
import com.athar.bms.customer.entity.Customer;
import com.athar.bms.customer.repository.CustomerRepository;
import com.athar.bms.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public CustomerResponse createCustomer(CustomerRequest request) {

        if (customerRepository.existsByName(request.getName())) {
            throw new RuntimeException("Customer already exists");
        }

        Customer customer = Customer.builder()
                .name(request.getName())
                .phone(request.getPhone())
                .email(request.getEmail())
                .address(request.getAddress())
                .gstNumber(request.getGstNumber())
                .build();

        Customer savedCustomer = customerRepository.save(customer);

        return mapToResponse(savedCustomer);
    }

    @Override
    public List<CustomerResponse> getAllCustomers() {

        return customerRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public CustomerResponse getCustomerById(Long id) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Customer not found"));

        return mapToResponse(customer);
    }

    @Override
    public CustomerResponse updateCustomer(
            Long id,
            CustomerRequest request) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Customer not found"));

        if (!customer.getName().equals(request.getName())
                && customerRepository.existsByName(request.getName())) {

            throw new RuntimeException("Customer already exists");
        }

        customer.setName(request.getName());
        customer.setPhone(request.getPhone());
        customer.setEmail(request.getEmail());
        customer.setAddress(request.getAddress());
        customer.setGstNumber(request.getGstNumber());

        Customer updatedCustomer = customerRepository.save(customer);

        return mapToResponse(updatedCustomer);
    }

    @Override
    public void deleteCustomer(Long id) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Customer not found"));

        customerRepository.delete(customer);
    }

    private CustomerResponse mapToResponse(Customer customer) {

        return CustomerResponse.builder()
                .id(customer.getId())
                .name(customer.getName())
                .phone(customer.getPhone())
                .email(customer.getEmail())
                .address(customer.getAddress())
                .gstNumber(customer.getGstNumber())
                .isActive(customer.getIsActive())
                .build();
    }
}