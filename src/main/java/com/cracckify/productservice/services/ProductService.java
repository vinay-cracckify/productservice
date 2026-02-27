package com.cracckify.productservice.services;

import com.cracckify.productservice.dto.ProductDTO;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    ProductDTO getProductById(UUID id);
    ProductDTO createProduct(ProductDTO productDTO);
    ProductDTO updateProduct(UUID id, ProductDTO productDTO);
    void deleteProduct(UUID id);
    List<ProductDTO> getAllProducts();

    List<ProductDTO> getProductsByCategory(UUID categoryId);

    List<ProductDTO> getAvailableProductsWithStock();
}

