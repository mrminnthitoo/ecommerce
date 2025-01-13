package com.minnthitoo.ecommerce.repositories;

import com.minnthitoo.ecommerce.models.entities.CategoryEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestCategoryRepository {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void testInsertCategory(){
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName("Category 1");
        categoryEntity.setDescription("Category 1 Description");

        CategoryEntity savedEntity = this.categoryRepository.save(categoryEntity);
        assertEquals("Category 1", savedEntity.getName());
    }

}
