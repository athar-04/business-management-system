package com.athar.bms.product.repository;

import com.athar.bms.brand.entity.Brand;
import com.athar.bms.category.entity.Category;
import com.athar.bms.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsBySku(String sku);

    Optional<Product> findBySku(String sku);

    List<Product> findByCategory(Category category);

    List<Product> findByBrand(Brand brand);
}