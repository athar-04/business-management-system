package com.athar.bms.purchase.controller;

import com.athar.bms.purchase.dto.PurchaseRequest;
import com.athar.bms.purchase.dto.PurchaseResponse;
import com.athar.bms.purchase.service.PurchaseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchases")
@RequiredArgsConstructor
public class PurchaseController {

    private final PurchaseService purchaseService;

    @PostMapping
    public PurchaseResponse createPurchase(
            @Valid @RequestBody PurchaseRequest request) {

        return purchaseService.createPurchase(request);
    }

    @GetMapping
    public List<PurchaseResponse> getAllPurchases() {

        return purchaseService.getAllPurchases();
    }

    @GetMapping("/{id}")
    public PurchaseResponse getPurchaseById(
            @PathVariable Long id) {

        return purchaseService.getPurchaseById(id);
    }

    @DeleteMapping("/{id}")
    public void deletePurchase(
            @PathVariable Long id) {

        purchaseService.deletePurchase(id);
    }
}