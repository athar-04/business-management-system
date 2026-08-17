package com.athar.bms.customerpayment.service;

import com.athar.bms.customerpayment.dto.CustomerPaymentRequest;
import com.athar.bms.customerpayment.dto.CustomerPaymentResponse;

import java.math.BigDecimal;
import java.util.List;

public interface CustomerPaymentService {

    CustomerPaymentResponse createPayment(CustomerPaymentRequest request);

    List<CustomerPaymentResponse> getPaymentsBySale(Long saleId);

    BigDecimal getOutstandingAmount(Long saleId);
}