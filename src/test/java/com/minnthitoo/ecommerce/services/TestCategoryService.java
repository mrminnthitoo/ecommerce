package com.minnthitoo.ecommerce.services;

import com.minnthitoo.ecommerce.models.dto.CategoryDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestCategoryService {

    @Autowired
    private CategoryService categoryService;

    @Test
    public void testGetAllCategories(){
        List<CategoryDto> categories = this.categoryService.getAllCategories();
        for (CategoryDto category : categories){
            log.info("Category {}", category);
        }
    }

    @Test
    public void testGetCategoryById(){
        Long categoryId = 1L;

        Optional<CategoryDto> category = this.categoryService.getCategoryById(categoryId);
        log.info("Category {}", category.get());

    }

    @Test
    public void testInsertCategory(){
        for (int i = 1; i <= 30; i++){
            CategoryDto categoryEntity = new CategoryDto();
            categoryEntity.setName("Category " + i);
            categoryEntity.setDescription("Category " + i + " description");
            this.categoryService.createCategory(categoryEntity);
        }
    }

    @Test
    public void testUpdateCategory(){
        CategoryDto category = this.categoryService.getCategoryById(1L).get();
        category.setName("Category 1 Update");
        category.setDescription("Category 1 description update");

        this.categoryService.updateCategory(category);

    }

    @Test
    public void testDeleteCategoryById(){
        Long categoryId = 1L;
        this.categoryService.deleteCategoryById(categoryId);
    }

}
