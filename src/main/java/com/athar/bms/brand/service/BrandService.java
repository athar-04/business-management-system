package com.athar.bms.brand.service;

import com.athar.bms.brand.dto.BrandRequest;
import com.athar.bms.brand.dto.BrandResponse;

import java.util.List;

public interface BrandService {

    BrandResponse createBrand(BrandRequest request);

    List<BrandResponse> getAllBrands();

    BrandResponse getBrandById(Long id);

    BrandResponse updateBrand(Long id, BrandRequest request);

    void deleteBrand(Long id);
}