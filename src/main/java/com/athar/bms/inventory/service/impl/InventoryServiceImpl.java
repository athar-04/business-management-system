package com.athar.bms.inventory.service.impl;

import com.athar.bms.godown.repository.GodownRepository;
import com.athar.bms.inventory.dto.InventoryRequest;
import com.athar.bms.inventory.dto.InventoryResponse;
import com.athar.bms.inventory.dto.LowStockResponse;
import com.athar.bms.inventory.entity.Inventory;
import com.athar.bms.godown.entity.Godown;
import com.athar.bms.product.entity.Product;
import com.athar.bms.inventory.repository.InventoryRepository;
import com.athar.bms.inventory.service.InventoryService;
import com.athar.bms.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    private final ProductRepository productRepository;

    private final GodownRepository godownRepository;

    @Override
    public InventoryResponse createInventory(InventoryRequest request) {

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() ->
                        new RuntimeException("Product not found"));

        Godown godown = godownRepository.findById(request.getGodownId())
                .orElseThrow(() ->
                        new RuntimeException("Godown not found"));

        Inventory inventory = inventoryRepository
                .findByProductAndGodown(product, godown)
                .orElse(null);

        if (inventory == null) {

            inventory = Inventory.builder()
                    .product(product)
                    .godown(godown)
                    .quantity(request.getQuantity())
                    .build();

        } else {

            inventory.setQuantity(
                    inventory.getQuantity() + request.getQuantity()
            );
        }

        Inventory savedInventory = inventoryRepository.save(inventory);

        return InventoryResponse.builder()
                .id(savedInventory.getId())
                .productId(savedInventory.getProduct().getId())
                .productName(savedInventory.getProduct().getName())
                .godownId(savedInventory.getGodown().getId())
                .godownName(savedInventory.getGodown().getName())
                .quantity(savedInventory.getQuantity())
                .build();
    }

    @Override
    public List<InventoryResponse> getAllInventory() {

        return inventoryRepository.findAll()
                .stream()
                .map(inventory -> InventoryResponse.builder()
                        .id(inventory.getId())
                        .productId(inventory.getProduct().getId())
                        .productName(inventory.getProduct().getName())
                        .godownId(inventory.getGodown().getId())
                        .godownName(inventory.getGodown().getName())
                        .quantity(inventory.getQuantity())
                        .build())
                .toList();
    }

    @Override
    public InventoryResponse getInventoryById(Long id) {

        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Inventory not found"));

        return InventoryResponse.builder()
                .id(inventory.getId())
                .productId(inventory.getProduct().getId())
                .productName(inventory.getProduct().getName())
                .godownId(inventory.getGodown().getId())
                .godownName(inventory.getGodown().getName())
                .quantity(inventory.getQuantity())
                .build();
    }

    @Override
    public InventoryResponse updateInventory(Long id, InventoryRequest request) {

        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Inventory not found"));

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() ->
                        new RuntimeException("Product not found"));

        Godown godown = godownRepository.findById(request.getGodownId())
                .orElseThrow(() ->
                        new RuntimeException("Godown not found"));

        inventory.setProduct(product);
        inventory.setGodown(godown);
        inventory.setQuantity(request.getQuantity());

        Inventory updatedInventory = inventoryRepository.save(inventory);

        return InventoryResponse.builder()
                .id(updatedInventory.getId())
                .productId(updatedInventory.getProduct().getId())
                .productName(updatedInventory.getProduct().getName())
                .godownId(updatedInventory.getGodown().getId())
                .godownName(updatedInventory.getGodown().getName())
                .quantity(updatedInventory.getQuantity())
                .build();
    }

    @Override
    public void deleteInventory(Long id) {

        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Inventory not found"));

        inventoryRepository.delete(inventory);
    }
    @Override
    public List<LowStockResponse> getLowStock(Integer threshold) {

        return inventoryRepository.findAll()
                .stream()
                .filter(inventory ->
                        inventory.getQuantity() <= threshold)
                .map(inventory -> LowStockResponse.builder()
                        .inventoryId(inventory.getId())
                        .productId(inventory.getProduct().getId())
                        .productName(inventory.getProduct().getName())
                        .godownId(inventory.getGodown().getId())
                        .godownName(inventory.getGodown().getName())
                        .currentQuantity(inventory.getQuantity())
                        .threshold(threshold)
                        .build())
                .toList();
    }
}