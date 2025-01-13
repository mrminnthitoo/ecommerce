package com.minnthitoo.ecommerce.repositories;

import com.minnthitoo.ecommerce.models.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
}
