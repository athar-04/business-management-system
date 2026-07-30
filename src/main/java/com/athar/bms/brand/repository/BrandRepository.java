package com.athar.bms.brand.repository;

import com.athar.bms.brand.entity.Brand;
import com.athar.bms.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BrandRepository extends JpaRepository<Brand, Long> {

    boolean existsByName(String name);

    Optional<Brand> findByName(String name);

    List<Brand> findByCategory(Category category);
}