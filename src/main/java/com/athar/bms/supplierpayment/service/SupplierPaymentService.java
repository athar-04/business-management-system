package com.athar.bms.supplierpayment.service;

import com.athar.bms.supplierpayment.dto.SupplierPaymentRequest;
import com.athar.bms.supplierpayment.dto.SupplierPaymentResponse;

import java.math.BigDecimal;
import java.util.List;

public interface SupplierPaymentService {

    SupplierPaymentResponse createPayment(
            SupplierPaymentRequest request);

    List<SupplierPaymentResponse> getPaymentsByPurchase(
            Long purchaseId);

    BigDecimal getOutstandingAmount(Long purchaseId);
}