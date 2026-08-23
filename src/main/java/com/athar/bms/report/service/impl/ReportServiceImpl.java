package com.athar.bms.report.service.impl;

import com.athar.bms.customerpayment.entity.CustomerPayment;
import com.athar.bms.customerpayment.repository.CustomerPaymentRepository;
import com.athar.bms.expense.entity.Expense;
import com.athar.bms.expense.repository.ExpenseRepository;
import com.athar.bms.purchase.entity.Purchase;
import com.athar.bms.purchase.repository.PurchaseRepository;
import com.athar.bms.report.dto.BusinessReportResponse;
import com.athar.bms.report.service.ReportService;
import com.athar.bms.sales.entity.Sale;
import com.athar.bms.sales.repository.SaleRepository;
import com.athar.bms.supplierpayment.entity.SupplierPayment;
import com.athar.bms.supplierpayment.repository.SupplierPaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final SaleRepository saleRepository;
    private final PurchaseRepository purchaseRepository;
    private final ExpenseRepository expenseRepository;
    private final CustomerPaymentRepository customerPaymentRepository;
    private final SupplierPaymentRepository supplierPaymentRepository;

    @Override
    public BusinessReportResponse getBusinessReport() {

        BigDecimal totalSales = saleRepository.findAll()
                .stream()
                .map(Sale::getTotalAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalPurchases = purchaseRepository.findAll()
                .stream()
                .map(Purchase::getTotalAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalExpenses = expenseRepository.findAll()
                .stream()
                .map(Expense::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalCustomerPayments = customerPaymentRepository.findAll()
                .stream()
                .map(CustomerPayment::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalSupplierPayments = supplierPaymentRepository.findAll()
                .stream()
                .map(SupplierPayment::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal netProfit = totalSales
                .subtract(totalPurchases)
                .subtract(totalExpenses);

        return BusinessReportResponse.builder()
                .totalSales(totalSales)
                .totalPurchases(totalPurchases)
                .totalExpenses(totalExpenses)
                .totalCustomerPayments(totalCustomerPayments)
                .totalSupplierPayments(totalSupplierPayments)
                .netProfit(netProfit)
                .build();
    }
}