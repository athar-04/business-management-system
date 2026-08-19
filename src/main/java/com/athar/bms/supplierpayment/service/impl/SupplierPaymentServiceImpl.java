package com.athar.bms.supplierpayment.service.impl;

import com.athar.bms.purchase.entity.Purchase;
import com.athar.bms.purchase.repository.PurchaseRepository;
import com.athar.bms.supplierpayment.dto.SupplierPaymentRequest;
import com.athar.bms.supplierpayment.dto.SupplierPaymentResponse;
import com.athar.bms.supplierpayment.entity.SupplierPayment;
import com.athar.bms.supplierpayment.repository.SupplierPaymentRepository;
import com.athar.bms.supplierpayment.service.SupplierPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplierPaymentServiceImpl
        implements SupplierPaymentService {

    private final SupplierPaymentRepository supplierPaymentRepository;
    private final PurchaseRepository purchaseRepository;

    @Override
    @Transactional
    public SupplierPaymentResponse createPayment(
            SupplierPaymentRequest request) {

        Purchase purchase = purchaseRepository.findById(request.getPurchaseId())
                .orElseThrow(() ->
                        new RuntimeException("Purchase not found"));

        BigDecimal alreadyPaid = supplierPaymentRepository
                .findByPurchase(purchase)
                .stream()
                .map(SupplierPayment::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal outstandingAmount = purchase.getTotalAmount()
                .subtract(alreadyPaid);

        if (request.getAmount().compareTo(outstandingAmount) > 0) {
            throw new RuntimeException(
                    "Payment exceeds outstanding amount");
        }

        SupplierPayment payment = SupplierPayment.builder()
                .purchase(purchase)
                .amount(request.getAmount())
                .paymentDate(request.getPaymentDate())
                .paymentMethod(request.getPaymentMethod())
                .notes(request.getNotes())
                .build();

        SupplierPayment savedPayment =
                supplierPaymentRepository.save(payment);

        return mapToResponse(savedPayment, purchase);
    }

    @Override
    public List<SupplierPaymentResponse> getPaymentsByPurchase(
            Long purchaseId) {

        Purchase purchase = purchaseRepository.findById(purchaseId)
                .orElseThrow(() ->
                        new RuntimeException("Purchase not found"));

        return supplierPaymentRepository.findByPurchase(purchase)
                .stream()
                .map(payment -> mapToResponse(payment, purchase))
                .toList();
    }

    @Override
    public BigDecimal getOutstandingAmount(Long purchaseId) {

        Purchase purchase = purchaseRepository.findById(purchaseId)
                .orElseThrow(() ->
                        new RuntimeException("Purchase not found"));

        BigDecimal alreadyPaid = supplierPaymentRepository
                .findByPurchase(purchase)
                .stream()
                .map(SupplierPayment::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return purchase.getTotalAmount()
                .subtract(alreadyPaid);
    }

    private SupplierPaymentResponse mapToResponse(
            SupplierPayment payment,
            Purchase purchase) {

        return SupplierPaymentResponse.builder()
                .id(payment.getId())
                .purchaseId(purchase.getId())
                .supplierId(purchase.getSupplier().getId())
                .supplierName(purchase.getSupplier().getName())
                .purchaseAmount(purchase.getTotalAmount())
                .amount(payment.getAmount())
                .paymentDate(payment.getPaymentDate())
                .paymentMethod(payment.getPaymentMethod())
                .notes(payment.getNotes())
                .build();
    }
}