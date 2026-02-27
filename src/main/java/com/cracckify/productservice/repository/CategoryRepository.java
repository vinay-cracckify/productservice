package com.cracckify.productservice.repository;

import com.cracckify.productservice.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
    boolean existsByTitle(String title);
    Optional<Category> findByTitle(String title);
}
