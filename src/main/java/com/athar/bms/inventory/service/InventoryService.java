package com.athar.bms.inventory.service;

import com.athar.bms.inventory.dto.InventoryRequest;
import com.athar.bms.inventory.dto.InventoryResponse;

import java.util.List;

public interface InventoryService {

    InventoryResponse createInventory(InventoryRequest request);

    List<InventoryResponse> getAllInventory();

    InventoryResponse getInventoryById(Long id);

    InventoryResponse updateInventory(Long id, InventoryRequest request);

    void deleteInventory(Long id);
}