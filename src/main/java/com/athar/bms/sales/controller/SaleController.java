package com.athar.bms.sales.controller;

import com.athar.bms.sales.dto.SaleRequest;
import com.athar.bms.sales.dto.SaleResponse;
import com.athar.bms.sales.service.SaleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sales")
@RequiredArgsConstructor
public class SaleController {

    private final SaleService saleService;

    @PostMapping
    public SaleResponse createSale(
            @Valid @RequestBody SaleRequest request) {

        return saleService.createSale(request);
    }

    @GetMapping
    public List<SaleResponse> getAllSales() {

        return saleService.getAllSales();
    }

    @GetMapping("/{id}")
    public SaleResponse getSaleById(
            @PathVariable Long id) {

        return saleService.getSaleById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteSale(
            @PathVariable Long id) {

        saleService.deleteSale(id);
    }
}