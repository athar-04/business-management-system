package com.athar.bms.supplierpayment.controller;

import com.athar.bms.supplierpayment.dto.SupplierPaymentRequest;
import com.athar.bms.supplierpayment.dto.SupplierPaymentResponse;
import com.athar.bms.supplierpayment.service.SupplierPaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/supplier-payments")
@RequiredArgsConstructor
public class SupplierPaymentController {

    private final SupplierPaymentService supplierPaymentService;

    @PostMapping
    public SupplierPaymentResponse createPayment(
            @Valid @RequestBody SupplierPaymentRequest request) {

        return supplierPaymentService.createPayment(request);
    }

    @GetMapping("/purchase/{purchaseId}")
    public List<SupplierPaymentResponse> getPaymentsByPurchase(
            @PathVariable Long purchaseId) {

        return supplierPaymentService.getPaymentsByPurchase(purchaseId);
    }

    @GetMapping("/purchase/{purchaseId}/outstanding")
    public BigDecimal getOutstandingAmount(
            @PathVariable Long purchaseId) {

        return supplierPaymentService.getOutstandingAmount(purchaseId);
    }
}