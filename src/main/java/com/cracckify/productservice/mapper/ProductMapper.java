package com.cracckify.productservice.mapper;

import com.cracckify.productservice.dto.ProductDTO;
import com.cracckify.productservice.models.Product;
import org.mapstruct.*;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", imports = UUID.class)
public interface ProductMapper {

    @Mapping(source = "category.id", target = "categoryId")
    ProductDTO toDto(Product product);

    @Mapping(target = "category", ignore = true)
    Product toEntity(ProductDTO dto);

    List<ProductDTO> toDtoList(List<Product> products);

    List<Product> toEntityList(List<ProductDTO> dtos);
}
