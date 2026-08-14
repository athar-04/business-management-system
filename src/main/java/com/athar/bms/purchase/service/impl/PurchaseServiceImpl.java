package com.athar.bms.purchase.service.impl;

import com.athar.bms.godown.entity.Godown;
import com.athar.bms.godown.repository.GodownRepository;
import com.athar.bms.inventory.entity.Inventory;
import com.athar.bms.inventory.repository.InventoryRepository;
import com.athar.bms.product.entity.Product;
import com.athar.bms.product.repository.ProductRepository;
import com.athar.bms.purchase.dto.PurchaseItemRequest;
import com.athar.bms.purchase.dto.PurchaseItemResponse;
import com.athar.bms.purchase.dto.PurchaseRequest;
import com.athar.bms.purchase.dto.PurchaseResponse;
import com.athar.bms.purchase.entity.Purchase;
import com.athar.bms.purchase.entity.PurchaseItem;
import com.athar.bms.purchase.repository.PurchaseRepository;
import com.athar.bms.purchase.service.PurchaseService;
import com.athar.bms.supplier.entity.Supplier;
import com.athar.bms.supplier.repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;

    private final SupplierRepository supplierRepository;

    private final ProductRepository productRepository;

    private final GodownRepository godownRepository;

    private final InventoryRepository inventoryRepository;

    @Transactional
    @Override
    public PurchaseResponse createPurchase(PurchaseRequest request) {

        Supplier supplier = supplierRepository.findById(request.getSupplierId())
                .orElseThrow(() ->
                        new RuntimeException("Supplier not found"));

        Godown godown = godownRepository.findById(request.getGodownId())
                .orElseThrow(() ->
                        new RuntimeException("Godown not found"));
        Purchase purchase = Purchase.builder()
                .supplier(supplier)
                .godown(godown)
                .purchaseDate(request.getPurchaseDate())
                .totalAmount(BigDecimal.ZERO)
                .build();

        BigDecimal totalAmount = BigDecimal.ZERO;

        for (PurchaseItemRequest itemRequest : request.getItems()) {

            Product product = productRepository.findById(itemRequest.getProductId())
                    .orElseThrow(() ->
                            new RuntimeException("Product not found"));

            BigDecimal subtotal = itemRequest.getPurchasePrice()
                    .multiply(BigDecimal.valueOf(itemRequest.getQuantity()));

            totalAmount = totalAmount.add(subtotal);

            PurchaseItem purchaseItem = PurchaseItem.builder()
                    .purchase(purchase)
                    .product(product)
                    .quantity(itemRequest.getQuantity())
                    .purchasePrice(itemRequest.getPurchasePrice())
                    .subtotal(subtotal)
                    .build();

            purchase.getItems().add(purchaseItem);

            Inventory inventory = inventoryRepository
                    .findByProductAndGodown(product, godown)
                    .orElse(null);

            if (inventory == null) {

                inventory = Inventory.builder()
                        .product(product)
                        .godown(godown)
                        .quantity(itemRequest.getQuantity())
                        .build();

            } else {

                inventory.setQuantity(
                        inventory.getQuantity() + itemRequest.getQuantity()
                );
            }

            inventoryRepository.save(inventory);
        }
        purchase.setTotalAmount(totalAmount);

        Purchase savedPurchase = purchaseRepository.save(purchase);

        return mapToResponse(savedPurchase);
    }

    @Override
    public List<PurchaseResponse> getAllPurchases() {

        return purchaseRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public PurchaseResponse getPurchaseById(Long id) {

        Purchase purchase = purchaseRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Purchase not found"));

        return mapToResponse(purchase);
    }

    @Override
    @Transactional
    public void deletePurchase(Long id) {

        Purchase purchase = purchaseRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Purchase not found"));

        purchaseRepository.delete(purchase);
    }

    private PurchaseResponse mapToResponse(Purchase purchase) {

        List<PurchaseItemResponse> itemResponses = purchase.getItems()
                .stream()
                .map(item -> PurchaseItemResponse.builder()
                        .id(item.getId())
                        .productId(item.getProduct().getId())
                        .productName(item.getProduct().getName())
                        .quantity(item.getQuantity())
                        .purchasePrice(item.getPurchasePrice())
                        .subtotal(item.getSubtotal())
                        .build())
                .toList();

        return PurchaseResponse.builder()
                .id(purchase.getId())
                .supplierId(purchase.getSupplier().getId())
                .supplierName(purchase.getSupplier().getName())
                .godownId(purchase.getGodown().getId())
                .godownName(purchase.getGodown().getName())
                .purchaseDate(purchase.getPurchaseDate())
                .totalAmount(purchase.getTotalAmount())
                .items(itemResponses)
                .build();
    }
}