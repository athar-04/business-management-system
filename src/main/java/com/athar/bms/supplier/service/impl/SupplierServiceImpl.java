package com.athar.bms.supplier.service.impl;

import com.athar.bms.supplier.dto.SupplierRequest;
import com.athar.bms.supplier.dto.SupplierResponse;
import com.athar.bms.supplier.entity.Supplier;
import com.athar.bms.supplier.repository.SupplierRepository;
import com.athar.bms.supplier.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;

    @Override
    public SupplierResponse createSupplier(SupplierRequest request) {

        if (supplierRepository.existsByName(request.getName())) {
            throw new RuntimeException("Supplier already exists");
        }

        Supplier supplier = Supplier.builder()
                .name(request.getName())
                .phone(request.getPhone())
                .email(request.getEmail())
                .address(request.getAddress())
                .gstNumber(request.getGstNumber())
                .build();

        Supplier savedSupplier = supplierRepository.save(supplier);

        return SupplierResponse.builder()
                .id(savedSupplier.getId())
                .name(savedSupplier.getName())
                .phone(savedSupplier.getPhone())
                .email(savedSupplier.getEmail())
                .address(savedSupplier.getAddress())
                .gstNumber(savedSupplier.getGstNumber())
                .isActive(savedSupplier.getIsActive())
                .build();
    }

    @Override
    public List<SupplierResponse> getAllSuppliers() {

        return supplierRepository.findAll()
                .stream()
                .map(supplier -> SupplierResponse.builder()
                        .id(supplier.getId())
                        .name(supplier.getName())
                        .phone(supplier.getPhone())
                        .email(supplier.getEmail())
                        .address(supplier.getAddress())
                        .gstNumber(supplier.getGstNumber())
                        .isActive(supplier.getIsActive())
                        .build())
                .toList();
    }

    @Override
    public SupplierResponse getSupplierById(Long id) {

        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Supplier not found"));

        return SupplierResponse.builder()
                .id(supplier.getId())
                .name(supplier.getName())
                .phone(supplier.getPhone())
                .email(supplier.getEmail())
                .address(supplier.getAddress())
                .gstNumber(supplier.getGstNumber())
                .isActive(supplier.getIsActive())
                .build();
    }

    @Override
    public SupplierResponse updateSupplier(Long id, SupplierRequest request) {

        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Supplier not found"));

        if (!supplier.getName().equals(request.getName())
                && supplierRepository.existsByName(request.getName())) {
            throw new RuntimeException("Supplier already exists");
        }

        supplier.setName(request.getName());
        supplier.setPhone(request.getPhone());
        supplier.setEmail(request.getEmail());
        supplier.setAddress(request.getAddress());
        supplier.setGstNumber(request.getGstNumber());

        Supplier updatedSupplier = supplierRepository.save(supplier);

        return SupplierResponse.builder()
                .id(updatedSupplier.getId())
                .name(updatedSupplier.getName())
                .phone(updatedSupplier.getPhone())
                .email(updatedSupplier.getEmail())
                .address(updatedSupplier.getAddress())
                .gstNumber(updatedSupplier.getGstNumber())
                .isActive(updatedSupplier.getIsActive())
                .build();
    }

    @Override
    public void deleteSupplier(Long id) {

        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Supplier not found"));

        supplierRepository.delete(supplier);
    }
}