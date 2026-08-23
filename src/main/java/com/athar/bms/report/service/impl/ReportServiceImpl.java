package com.athar.bms.report.service.impl;

import com.athar.bms.customer.entity.Customer;
import com.athar.bms.customerpayment.entity.CustomerPayment;
import com.athar.bms.customerpayment.repository.CustomerPaymentRepository;
import com.athar.bms.expense.entity.Expense;
import com.athar.bms.expense.repository.ExpenseRepository;
import com.athar.bms.purchase.entity.Purchase;
import com.athar.bms.purchase.repository.PurchaseRepository;
import com.athar.bms.report.dto.BusinessReportResponse;
import com.athar.bms.report.dto.CustomerOutstandingResponse;
import com.athar.bms.report.dto.SupplierOutstandingResponse;
import com.athar.bms.report.service.ReportService;
import com.athar.bms.sales.entity.Sale;
import com.athar.bms.sales.repository.SaleRepository;
import com.athar.bms.supplier.entity.Supplier;
import com.athar.bms.supplierpayment.entity.SupplierPayment;
import com.athar.bms.supplierpayment.repository.SupplierPaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

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

    @Override
    public List<CustomerOutstandingResponse> getCustomerOutstandings() {

        return saleRepository.findAll()
                .stream()
                .map(Sale::getCustomer)
                .distinct()
                .map(this::calculateCustomerOutstanding)
                .filter(report ->
                        report.getOutstandingAmount()
                                .compareTo(BigDecimal.ZERO) > 0)
                .toList();
    }

    private CustomerOutstandingResponse calculateCustomerOutstanding(
            Customer customer) {

        List<Sale> sales = saleRepository.findByCustomer(customer);

        BigDecimal totalSales = sales.stream()
                .map(Sale::getTotalAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalPaid = sales.stream()
                .flatMap(sale ->
                        customerPaymentRepository.findBySale(sale).stream())
                .map(CustomerPayment::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal outstandingAmount =
                totalSales.subtract(totalPaid);

        return CustomerOutstandingResponse.builder()
                .customerId(customer.getId())
                .customerName(customer.getName())
                .totalSales(totalSales)
                .totalPaid(totalPaid)
                .outstandingAmount(outstandingAmount)
                .build();
    }

    @Override
    public List<SupplierOutstandingResponse> getSupplierOutstandings() {

        return purchaseRepository.findAll()
                .stream()
                .map(Purchase::getSupplier)
                .distinct()
                .map(this::calculateSupplierOutstanding)
                .filter(report ->
                        report.getOutstandingAmount()
                                .compareTo(BigDecimal.ZERO) > 0)
                .toList();
    }

    private SupplierOutstandingResponse calculateSupplierOutstanding(
            Supplier supplier) {

        List<Purchase> purchases =
                purchaseRepository.findBySupplier(supplier);

        BigDecimal totalPurchases = purchases.stream()
                .map(Purchase::getTotalAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalPaid = purchases.stream()
                .flatMap(purchase ->
                        supplierPaymentRepository
                                .findByPurchase(purchase)
                                .stream())
                .map(SupplierPayment::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal outstandingAmount =
                totalPurchases.subtract(totalPaid);

        return SupplierOutstandingResponse.builder()
                .supplierId(supplier.getId())
                .supplierName(supplier.getName())
                .totalPurchases(totalPurchases)
                .totalPaid(totalPaid)
                .outstandingAmount(outstandingAmount)
                .build();
    }
}