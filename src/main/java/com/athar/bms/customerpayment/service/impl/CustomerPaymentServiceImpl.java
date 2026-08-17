package com.athar.bms.customerpayment.service.impl;

import com.athar.bms.customerpayment.dto.CustomerPaymentRequest;
import com.athar.bms.customerpayment.dto.CustomerPaymentResponse;
import com.athar.bms.customerpayment.entity.CustomerPayment;
import com.athar.bms.customerpayment.repository.CustomerPaymentRepository;
import com.athar.bms.customerpayment.service.CustomerPaymentService;
import com.athar.bms.sales.entity.Sale;
import com.athar.bms.sales.repository.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerPaymentServiceImpl
        implements CustomerPaymentService {

    private final CustomerPaymentRepository customerPaymentRepository;
    private final SaleRepository saleRepository;

    @Override
    @Transactional
    public CustomerPaymentResponse createPayment(
            CustomerPaymentRequest request) {

        Sale sale = saleRepository.findById(request.getSaleId())
                .orElseThrow(() ->
                        new RuntimeException("Sale not found"));

        BigDecimal alreadyPaid = customerPaymentRepository
                .findBySale(sale)
                .stream()
                .map(CustomerPayment::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal outstandingAmount = sale.getTotalAmount()
                .subtract(alreadyPaid);

        if (request.getAmount().compareTo(outstandingAmount) > 0) {
            throw new RuntimeException(
                    "Payment exceeds outstanding amount");
        }

        CustomerPayment payment = CustomerPayment.builder()
                .sale(sale)
                .amount(request.getAmount())
                .paymentDate(request.getPaymentDate())
                .paymentMethod(request.getPaymentMethod())
                .notes(request.getNotes())
                .build();

        CustomerPayment savedPayment =
                customerPaymentRepository.save(payment);

        return mapToResponse(savedPayment, sale);
    }

    @Override
    public List<CustomerPaymentResponse> getPaymentsBySale(
            Long saleId) {

        Sale sale = saleRepository.findById(saleId)
                .orElseThrow(() ->
                        new RuntimeException("Sale not found"));

        return customerPaymentRepository.findBySale(sale)
                .stream()
                .map(payment -> mapToResponse(payment, sale))
                .toList();
    }

    @Override
    public BigDecimal getOutstandingAmount(Long saleId) {

        Sale sale = saleRepository.findById(saleId)
                .orElseThrow(() ->
                        new RuntimeException("Sale not found"));

        BigDecimal alreadyPaid = customerPaymentRepository
                .findBySale(sale)
                .stream()
                .map(CustomerPayment::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return sale.getTotalAmount()
                .subtract(alreadyPaid);
    }

    private CustomerPaymentResponse mapToResponse(
            CustomerPayment payment,
            Sale sale) {

        return CustomerPaymentResponse.builder()
                .id(payment.getId())
                .saleId(sale.getId())
                .customerId(sale.getCustomer().getId())
                .customerName(sale.getCustomer().getName())
                .saleAmount(sale.getTotalAmount())
                .amount(payment.getAmount())
                .paymentDate(payment.getPaymentDate())
                .paymentMethod(payment.getPaymentMethod())
                .notes(payment.getNotes())
                .build();
    }
}