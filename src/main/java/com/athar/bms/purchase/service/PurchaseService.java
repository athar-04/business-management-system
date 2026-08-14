package com.athar.bms.purchase.service;

import com.athar.bms.purchase.dto.PurchaseRequest;
import com.athar.bms.purchase.dto.PurchaseResponse;

import java.util.List;

public interface PurchaseService {

    PurchaseResponse createPurchase(PurchaseRequest request);

    List<PurchaseResponse> getAllPurchases();

    PurchaseResponse getPurchaseById(Long id);

    void deletePurchase(Long id);
}