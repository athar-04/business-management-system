package com.athar.bms.product.service.impl;

import com.athar.bms.brand.repository.BrandRepository;
import com.athar.bms.category.repository.CategoryRepository;
import com.athar.bms.product.dto.ProductRequest;
import com.athar.bms.product.dto.ProductResponse;
import com.athar.bms.product.entity.Product;
import com.athar.bms.category.entity.Category;
import com.athar.bms.brand.entity.Brand;
import com.athar.bms.product.repository.ProductRepository;
import com.athar.bms.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    private final BrandRepository brandRepository;

    @Override
    public ProductResponse createProduct(ProductRequest request) {

        if (productRepository.existsBySku(request.getSku())) {
            throw new RuntimeException("SKU already exists");
        }

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() ->
                        new RuntimeException("Category not found"));

        Brand brand = brandRepository.findById(request.getBrandId())
                .orElseThrow(() ->
                        new RuntimeException("Brand not found"));

        Product product = Product.builder()
                .name(request.getName())
                .sku(request.getSku())
                .description(request.getDescription())
                .purchasePrice(request.getPurchasePrice())
                .sellingPrice(request.getSellingPrice())
                .unit(request.getUnit())
                .minimumStock(request.getMinimumStock())
                .category(category)
                .brand(brand)
                .build();

        Product savedProduct = productRepository.save(product);

        return ProductResponse.builder()
                .id(savedProduct.getId())
                .name(savedProduct.getName())
                .sku(savedProduct.getSku())
                .description(savedProduct.getDescription())
                .purchasePrice(savedProduct.getPurchasePrice())
                .sellingPrice(savedProduct.getSellingPrice())
                .unit(savedProduct.getUnit())
                .minimumStock(savedProduct.getMinimumStock())
                .isActive(savedProduct.getIsActive())
                .categoryId(savedProduct.getCategory().getId())
                .categoryName(savedProduct.getCategory().getName())
                .brandId(savedProduct.getBrand().getId())
                .brandName(savedProduct.getBrand().getName())
                .build();
    }

    @Override
    public List<ProductResponse> getAllProducts() {

        return productRepository.findAll()
                .stream()
                .map(product -> ProductResponse.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .sku(product.getSku())
                        .description(product.getDescription())
                        .purchasePrice(product.getPurchasePrice())
                        .sellingPrice(product.getSellingPrice())
                        .unit(product.getUnit())
                        .minimumStock(product.getMinimumStock())
                        .isActive(product.getIsActive())
                        .categoryId(product.getCategory().getId())
                        .categoryName(product.getCategory().getName())
                        .brandId(product.getBrand().getId())
                        .brandName(product.getBrand().getName())
                        .build())
                .toList();
    }

    @Override
    public ProductResponse getProductById(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Product not found"));

        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .sku(product.getSku())
                .description(product.getDescription())
                .purchasePrice(product.getPurchasePrice())
                .sellingPrice(product.getSellingPrice())
                .unit(product.getUnit())
                .minimumStock(product.getMinimumStock())
                .isActive(product.getIsActive())
                .categoryId(product.getCategory().getId())
                .categoryName(product.getCategory().getName())
                .brandId(product.getBrand().getId())
                .brandName(product.getBrand().getName())
                .build();
    }

    @Override
    public ProductResponse updateProduct(Long id, ProductRequest request) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Product not found"));

        if (!product.getSku().equals(request.getSku())
                && productRepository.existsBySku(request.getSku())) {
            throw new RuntimeException("SKU already exists");
        }

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() ->
                        new RuntimeException("Category not found"));

        Brand brand = brandRepository.findById(request.getBrandId())
                .orElseThrow(() ->
                        new RuntimeException("Brand not found"));

        product.setName(request.getName());
        product.setSku(request.getSku());
        product.setDescription(request.getDescription());
        product.setPurchasePrice(request.getPurchasePrice());
        product.setSellingPrice(request.getSellingPrice());
        product.setUnit(request.getUnit());
        product.setMinimumStock(request.getMinimumStock());
        product.setCategory(category);
        product.setBrand(brand);

        Product updatedProduct = productRepository.save(product);

        return ProductResponse.builder()
                .id(updatedProduct.getId())
                .name(updatedProduct.getName())
                .sku(updatedProduct.getSku())
                .description(updatedProduct.getDescription())
                .purchasePrice(updatedProduct.getPurchasePrice())
                .sellingPrice(updatedProduct.getSellingPrice())
                .unit(updatedProduct.getUnit())
                .minimumStock(updatedProduct.getMinimumStock())
                .isActive(updatedProduct.getIsActive())
                .categoryId(updatedProduct.getCategory().getId())
                .categoryName(updatedProduct.getCategory().getName())
                .brandId(updatedProduct.getBrand().getId())
                .brandName(updatedProduct.getBrand().getName())
                .build();
    }

    @Override
    public void deleteProduct(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Product not found"));

        productRepository.delete(product);
    }
}