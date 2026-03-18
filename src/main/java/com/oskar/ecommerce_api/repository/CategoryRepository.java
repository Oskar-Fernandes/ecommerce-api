package com.oskar.ecommerce_api.repository;

import com.oskar.ecommerce_api.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}