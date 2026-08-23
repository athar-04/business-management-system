package com.athar.bms.inventory.controller;

import com.athar.bms.inventory.dto.InventoryRequest;
import com.athar.bms.inventory.dto.InventoryResponse;
import com.athar.bms.inventory.dto.LowStockResponse;
import com.athar.bms.inventory.service.InventoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping
    public InventoryResponse createInventory(
            @Valid @RequestBody InventoryRequest request) {

        return inventoryService.createInventory(request);
    }

    @GetMapping
    public List<InventoryResponse> getAllInventory() {

        return inventoryService.getAllInventory();
    }

    @GetMapping("/{id}")
    public InventoryResponse getInventoryById(
            @PathVariable Long id) {

        return inventoryService.getInventoryById(id);
    }

    @PutMapping("/{id}")
    public InventoryResponse updateInventory(
            @PathVariable Long id,
            @Valid @RequestBody InventoryRequest request) {

        return inventoryService.updateInventory(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteInventory(
            @PathVariable Long id) {

        inventoryService.deleteInventory(id);
    }

    @GetMapping("/low-stock")
    public ResponseEntity<List<LowStockResponse>> getLowStock(
            @RequestParam Integer threshold) {

        return ResponseEntity.ok(
                inventoryService.getLowStock(threshold)
        );
    }
}