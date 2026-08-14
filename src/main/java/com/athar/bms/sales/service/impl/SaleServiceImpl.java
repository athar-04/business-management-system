package com.athar.bms.sales.service.impl;

import com.athar.bms.customer.entity.Customer;
import com.athar.bms.customer.repository.CustomerRepository;
import com.athar.bms.godown.entity.Godown;
import com.athar.bms.godown.repository.GodownRepository;
import com.athar.bms.inventory.entity.Inventory;
import com.athar.bms.inventory.repository.InventoryRepository;
import com.athar.bms.product.entity.Product;
import com.athar.bms.product.repository.ProductRepository;
import com.athar.bms.sales.dto.SaleItemRequest;
import com.athar.bms.sales.dto.SaleItemResponse;
import com.athar.bms.sales.dto.SaleRequest;
import com.athar.bms.sales.dto.SaleResponse;
import com.athar.bms.sales.entity.Sale;
import com.athar.bms.sales.entity.SaleItem;
import com.athar.bms.sales.repository.SaleRepository;
import com.athar.bms.sales.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final GodownRepository godownRepository;
    private final InventoryRepository inventoryRepository;

    @Override
    @Transactional
    public SaleResponse createSale(SaleRequest request) {

        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() ->
                        new RuntimeException("Customer not found"));

        Godown godown = godownRepository.findById(request.getGodownId())
                .orElseThrow(() ->
                        new RuntimeException("Godown not found"));

        Sale sale = Sale.builder()
                .customer(customer)
                .godown(godown)
                .saleDate(request.getSaleDate())
                .totalAmount(BigDecimal.ZERO)
                .build();

        BigDecimal totalAmount = BigDecimal.ZERO;

        for (SaleItemRequest itemRequest : request.getItems()) {

            Product product = productRepository.findById(itemRequest.getProductId())
                    .orElseThrow(() ->
                            new RuntimeException("Product not found"));

            Inventory inventory = inventoryRepository
                    .findByProductAndGodown(product, godown)
                    .orElseThrow(() ->
                            new RuntimeException("Inventory not found"));

            if (inventory.getQuantity() < itemRequest.getQuantity()) {
                throw new RuntimeException(
                        "Insufficient stock for product: " + product.getName());
            }

            BigDecimal subtotal = itemRequest.getSellingPrice()
                    .multiply(BigDecimal.valueOf(itemRequest.getQuantity()));

            totalAmount = totalAmount.add(subtotal);

            SaleItem saleItem = SaleItem.builder()
                    .sale(sale)
                    .product(product)
                    .quantity(itemRequest.getQuantity())
                    .sellingPrice(itemRequest.getSellingPrice())
                    .subtotal(subtotal)
                    .build();

            sale.getItems().add(saleItem);

            inventory.setQuantity(
                    inventory.getQuantity() - itemRequest.getQuantity()
            );

            inventoryRepository.save(inventory);
        }

        sale.setTotalAmount(totalAmount);

        Sale savedSale = saleRepository.save(sale);

        return mapToResponse(savedSale);
    }

    @Override
    public List<SaleResponse> getAllSales() {

        return saleRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public SaleResponse getSaleById(Long id) {

        Sale sale = saleRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Sale not found"));

        return mapToResponse(sale);
    }

    @Override
    @Transactional
    public void deleteSale(Long id) {

        Sale sale = saleRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Sale not found"));

        saleRepository.delete(sale);
    }

    private SaleResponse mapToResponse(Sale sale) {

        List<SaleItemResponse> itemResponses = sale.getItems()
                .stream()
                .map(item -> SaleItemResponse.builder()
                        .id(item.getId())
                        .productId(item.getProduct().getId())
                        .productName(item.getProduct().getName())
                        .quantity(item.getQuantity())
                        .sellingPrice(item.getSellingPrice())
                        .subtotal(item.getSubtotal())
                        .build())
                .toList();

        return SaleResponse.builder()
                .id(sale.getId())
                .customerId(sale.getCustomer().getId())
                .customerName(sale.getCustomer().getName())
                .godownId(sale.getGodown().getId())
                .godownName(sale.getGodown().getName())
                .saleDate(sale.getSaleDate())
                .totalAmount(sale.getTotalAmount())
                .items(itemResponses)
                .build();
    }
}