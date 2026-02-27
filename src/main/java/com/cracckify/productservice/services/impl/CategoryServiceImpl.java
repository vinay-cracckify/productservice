package com.cracckify.productservice.services.impl;

import com.cracckify.productservice.dto.CategoryDTO;
import com.cracckify.productservice.exception.BadRequestException;
import com.cracckify.productservice.exception.DuplicateResourceException;
import com.cracckify.productservice.exception.ResourceNotFoundException;
import com.cracckify.productservice.mapper.CategoryMapper;
import com.cracckify.productservice.models.Category;
import com.cracckify.productservice.repository.CategoryRepository;
import com.cracckify.productservice.services.CategoryService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryDTO getCategoryById(UUID id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + id));
        return categoryMapper.toDto(category);
    }

    // Internal method to get entity
    public Category getCategoryEntityById(UUID id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + id));
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO dto) {
        if (!StringUtils.hasText(dto.getTitle())) {
            throw new BadRequestException("Category title must not be blank");
        }
        if (categoryRepository.existsByTitle(dto.getTitle())) {
            throw new DuplicateResourceException("Category title '" + dto.getTitle() + "' already exists");
        }

        Category category = categoryMapper.toEntity(dto);

        category = categoryRepository.save(category);

        return categoryMapper.toDto(category);
    }

    @Override
    public CategoryDTO updateCategory(UUID id, CategoryDTO dto) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + id));

        if (dto.getTitle() != null && !dto.getTitle().equals(existingCategory.getTitle())) {
            if (categoryRepository.existsByTitle(dto.getTitle())) {
                throw new DuplicateResourceException("Category title '" + dto.getTitle() + "' already exists");
            }
            existingCategory.setTitle(dto.getTitle());
        }

        if (dto.getDescription() != null) {
            existingCategory.setDescription(dto.getDescription());
        }

        Category updated = categoryRepository.save(existingCategory);
        return categoryMapper.toDto(updated);
    }

    @Override
    public void deleteCategory(UUID id) {
        if (!categoryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Category not found with id " + id);
        }
        categoryRepository.deleteById(id);
    }

    @Override
    public List<CategoryDTO> getAllCategory() {
        List<Category> categories = categoryRepository.findAll();
        return categoryMapper.toDtoList(categories);
    }

    @Override
    public Optional<CategoryDTO> findCategoryByTitle(String title) {
        return categoryRepository.findByTitle(title)
                .map(categoryMapper::toDto);
    }
}
