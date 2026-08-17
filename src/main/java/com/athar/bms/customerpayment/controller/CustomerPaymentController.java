package com.athar.bms.customerpayment.controller;

import com.athar.bms.customerpayment.dto.CustomerPaymentRequest;
import com.athar.bms.customerpayment.dto.CustomerPaymentResponse;
import com.athar.bms.customerpayment.service.CustomerPaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/customer-payments")
@RequiredArgsConstructor
public class CustomerPaymentController {

    private final CustomerPaymentService customerPaymentService;

    @PostMapping
    public CustomerPaymentResponse createPayment(
            @Valid @RequestBody CustomerPaymentRequest request) {

        return customerPaymentService.createPayment(request);
    }

    @GetMapping("/sale/{saleId}")
    public List<CustomerPaymentResponse> getPaymentsBySale(
            @PathVariable Long saleId) {

        return customerPaymentService.getPaymentsBySale(saleId);
    }

    @GetMapping("/sale/{saleId}/outstanding")
    public BigDecimal getOutstandingAmount(
            @PathVariable Long saleId) {

        return customerPaymentService.getOutstandingAmount(saleId);
    }
}