package com.athar.bms.customer.controller;

import com.athar.bms.customer.dto.CustomerRequest;
import com.athar.bms.customer.dto.CustomerResponse;
import com.athar.bms.customer.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public CustomerResponse createCustomer(
            @Valid @RequestBody CustomerRequest request) {

        return customerService.createCustomer(request);
    }

    @GetMapping
    public List<CustomerResponse> getAllCustomers() {

        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public CustomerResponse getCustomerById(
            @PathVariable Long id) {

        return customerService.getCustomerById(id);
    }

    @PutMapping("/{id}")
    public CustomerResponse updateCustomer(
            @PathVariable Long id,
            @Valid @RequestBody CustomerRequest request) {

        return customerService.updateCustomer(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(
            @PathVariable Long id) {

        customerService.deleteCustomer(id);
    }
}