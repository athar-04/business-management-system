package com.athar.bms.supplier.controller;

import com.athar.bms.supplier.dto.SupplierRequest;
import com.athar.bms.supplier.dto.SupplierResponse;
import com.athar.bms.supplier.service.SupplierService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
@RequiredArgsConstructor
public class SupplierController {

    private final SupplierService supplierService;

    @PostMapping
    public SupplierResponse createSupplier(
            @Valid @RequestBody SupplierRequest request) {

        return supplierService.createSupplier(request);
    }

    @GetMapping
    public List<SupplierResponse> getAllSuppliers() {

        return supplierService.getAllSuppliers();
    }

    @GetMapping("/{id}")
    public SupplierResponse getSupplierById(
            @PathVariable Long id) {

        return supplierService.getSupplierById(id);
    }

    @PutMapping("/{id}")
    public SupplierResponse updateSupplier(
            @PathVariable Long id,
            @Valid @RequestBody SupplierRequest request) {

        return supplierService.updateSupplier(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteSupplier(
            @PathVariable Long id) {

        supplierService.deleteSupplier(id);
    }
}