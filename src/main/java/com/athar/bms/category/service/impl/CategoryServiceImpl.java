package com.athar.bms.category.service.impl;

import com.athar.bms.category.dto.CategoryRequest;
import com.athar.bms.category.dto.CategoryResponse;
import com.athar.bms.category.entity.Category;
import com.athar.bms.category.repository.CategoryRepository;
import com.athar.bms.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryResponse createCategory(CategoryRequest request) {

        if (categoryRepository.existsByName(request.getName())) {
            throw new RuntimeException("Category already exists");
        }

        Category category = Category.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();

        Category savedCategory = categoryRepository.save(category);

        return CategoryResponse.builder()
                .id(savedCategory.getId())
                .name(savedCategory.getName())
                .description(savedCategory.getDescription())
                .isActive(savedCategory.getIsActive())
                .build();
    }

    @Override
    public List<CategoryResponse> getAllCategories() {

        return categoryRepository.findAll()
                .stream()
                .map(category -> CategoryResponse.builder()
                        .id(category.getId())
                        .name(category.getName())
                        .description(category.getDescription())
                        .isActive(category.getIsActive())
                        .build())
                .toList();
    }

    @Override
    public CategoryResponse getCategoryById(Long id) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Category not found"));

        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .isActive(category.getIsActive())
                .build();
    }

    @Override
    public CategoryResponse updateCategory(Long id, CategoryRequest request) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Category not found"));

        if (!category.getName().equals(request.getName())
                && categoryRepository.existsByName(request.getName())) {
            throw new RuntimeException("Category already exists");
        }

        category.setName(request.getName());
        category.setDescription(request.getDescription());

        Category updatedCategory = categoryRepository.save(category);

        return CategoryResponse.builder()
                .id(updatedCategory.getId())
                .name(updatedCategory.getName())
                .description(updatedCategory.getDescription())
                .isActive(updatedCategory.getIsActive())
                .build();
    }

    @Override
    public void deleteCategory(Long id) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Category not found"));

        categoryRepository.delete(category);
    }
}