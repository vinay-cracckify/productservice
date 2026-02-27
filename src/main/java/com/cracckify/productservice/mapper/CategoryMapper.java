package com.cracckify.productservice.mapper;

import com.cracckify.productservice.models.Category;
import com.cracckify.productservice.dto.CategoryDTO;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring",imports = UUID.class)
public interface CategoryMapper {
    CategoryDTO toDto(Category category);
    Category toEntity(CategoryDTO dto);
    List<CategoryDTO> toDtoList(List<Category> list);
    List<Category> toEntityList(List<CategoryDTO> list);
}
