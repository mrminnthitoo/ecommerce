package com.minnthitoo.ecommerce.services.implementations;

import com.minnthitoo.ecommerce.common.Mapper;
import com.minnthitoo.ecommerce.models.dto.CategoryDto;
import com.minnthitoo.ecommerce.models.entities.CategoryEntity;
import com.minnthitoo.ecommerce.repositories.CategoryRepository;
import com.minnthitoo.ecommerce.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImplementation implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private Mapper mapper;

    @Override
    public List<CategoryDto> getAllCategories() {
        List<CategoryEntity> categoryEntities = this.categoryRepository.findAll();
        return this.mapper.mapList( categoryEntities, CategoryDto.class);
    }

    @Override
    public Optional<CategoryDto> getCategoryById(Long categoryId) {
        Optional<CategoryEntity> result = this.categoryRepository.findById(categoryId);
        return result.map(categoryEntity -> this.mapper.map(categoryEntity, CategoryDto.class));
    }

    @Override
    public CategoryDto createCategory(CategoryDto category) {

        CategoryEntity createdCategory = this.categoryRepository.save(this.mapper.map(category, CategoryEntity.class));
        return this.mapper.map(createdCategory, CategoryDto.class);

    }

    @Override
    public CategoryDto updateCategory(CategoryDto category) {
        CategoryEntity updateCategory = this.categoryRepository.findById(category.getId()).get();
        updateCategory.setName(category.getName());
        updateCategory.setDescription(category.getDescription());

        CategoryEntity updatedCategory = this.categoryRepository.save(updateCategory);
        return this.mapper.map(updatedCategory, CategoryDto.class);
    }

    @Override
    public CategoryDto deleteCategoryById(Long categoryId) {

        CategoryEntity deleteCategory = this.categoryRepository.findById(categoryId).get();
        this.categoryRepository.delete(deleteCategory);

        return this.mapper.map(deleteCategory, CategoryDto.class);

    }
}
