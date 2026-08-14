package com.athar.bms.sales.service;

import com.athar.bms.sales.dto.SaleRequest;
import com.athar.bms.sales.dto.SaleResponse;

import java.util.List;

public interface SaleService {

    SaleResponse createSale(SaleRequest request);

    List<SaleResponse> getAllSales();

    SaleResponse getSaleById(Long id);

    void deleteSale(Long id);
}