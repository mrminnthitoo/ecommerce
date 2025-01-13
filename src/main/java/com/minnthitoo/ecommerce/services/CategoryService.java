package com.minnthitoo.ecommerce.services;

import com.minnthitoo.ecommerce.models.dto.CategoryDto;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<CategoryDto> getAllCategories();
    Optional<CategoryDto> getCategoryById(Long categoryId);
    CategoryDto createCategory(CategoryDto category);
    CategoryDto updateCategory(CategoryDto category);
    CategoryDto deleteCategoryById(Long categoryId);

}
