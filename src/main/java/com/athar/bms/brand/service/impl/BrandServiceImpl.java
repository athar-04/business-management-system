package com.athar.bms.brand.service.impl;

import com.athar.bms.brand.dto.BrandRequest;
import com.athar.bms.brand.dto.BrandResponse;
import com.athar.bms.brand.entity.Brand;
import com.athar.bms.brand.repository.BrandRepository;
import com.athar.bms.brand.service.BrandService;
import com.athar.bms.category.entity.Category;
import com.athar.bms.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public BrandResponse createBrand(BrandRequest request) {

        if (brandRepository.existsByName(request.getName())) {
            throw new RuntimeException("Brand already exists");
        }

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() ->
                        new RuntimeException("Category not found"));

        Brand brand = Brand.builder()
                .name(request.getName())
                .description(request.getDescription())
                .category(category)
                .build();

        Brand savedBrand = brandRepository.save(brand);

        return BrandResponse.builder()
                .id(savedBrand.getId())
                .name(savedBrand.getName())
                .description(savedBrand.getDescription())
                .isActive(savedBrand.getIsActive())
                .categoryId(savedBrand.getCategory().getId())
                .categoryName(savedBrand.getCategory().getName())
                .build();
    }

    @Override
    public List<BrandResponse> getAllBrands() {

        return brandRepository.findAll()
                .stream()
                .map(brand -> BrandResponse.builder()
                        .id(brand.getId())
                        .name(brand.getName())
                        .description(brand.getDescription())
                        .isActive(brand.getIsActive())
                        .categoryId(brand.getCategory().getId())
                        .categoryName(brand.getCategory().getName())
                        .build())
                .toList();
    }

    @Override
    public BrandResponse getBrandById(Long id) {

        Brand brand = brandRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Brand not found"));

        return BrandResponse.builder()
                .id(brand.getId())
                .name(brand.getName())
                .description(brand.getDescription())
                .isActive(brand.getIsActive())
                .categoryId(brand.getCategory().getId())
                .categoryName(brand.getCategory().getName())
                .build();
    }

    @Override
    public BrandResponse updateBrand(Long id, BrandRequest request) {

        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Brand not found"));

        if (!brand.getName().equals(request.getName())
                && brandRepository.existsByName(request.getName())) {
            throw new RuntimeException("Brand already exists");
        }

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        brand.setName(request.getName());
        brand.setDescription(request.getDescription());
        brand.setCategory(category);

        Brand updatedBrand = brandRepository.save(brand);

        return BrandResponse.builder()
                .id(updatedBrand.getId())
                .name(updatedBrand.getName())
                .description(updatedBrand.getDescription())
                .isActive(updatedBrand.getIsActive())
                .categoryId(updatedBrand.getCategory().getId())
                .categoryName(updatedBrand.getCategory().getName())
                .build();
    }

    @Override
    public void deleteBrand(Long id) {

        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Brand not found"));

        brandRepository.delete(brand);
    }
}