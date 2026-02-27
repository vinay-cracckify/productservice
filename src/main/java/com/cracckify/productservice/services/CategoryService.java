package com.cracckify.productservice.services;

import com.cracckify.productservice.dto.CategoryDTO;
import com.cracckify.productservice.models.Category;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryService {
    CategoryDTO getCategoryById(UUID id);
    // add this for internal entity retrieval
    Category getCategoryEntityById(UUID id);
    CategoryDTO createCategory(CategoryDTO dto);
    CategoryDTO updateCategory(UUID id, CategoryDTO dto);
    void deleteCategory(UUID id);
    List<CategoryDTO> getAllCategory();
    Optional<CategoryDTO> findCategoryByTitle(String title);
}
