package com.cracckify.productservice.services.impl;

import com.cracckify.productservice.dto.ProductDTO;
import com.cracckify.productservice.exception.ResourceNotFoundException;
import com.cracckify.productservice.mapper.ProductMapper;
import com.cracckify.productservice.models.Category;
import com.cracckify.productservice.models.Product;
import com.cracckify.productservice.repository.ProductRepository;
import com.cracckify.productservice.services.CategoryService;
import com.cracckify.productservice.services.ProductService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class SelfStoreProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryService categoryService;

    @Override
    public ProductDTO getProductById(UUID id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));
        return productMapper.toDto(product);
    }

    @Override
    public ProductDTO createProduct(ProductDTO dto) {
        if (!StringUtils.hasText(dto.getTitle())) {
            throw new IllegalArgumentException("Product title must not be blank");
        }
        // Validate category exists
        Category category = categoryService.getCategoryEntityById((dto.getCategoryId()));
        System.out.println(dto.isAvailable());
        Product product = productMapper.toEntity(dto);
        System.out.println(product.isAvailable());

        product.setCategory(category);
        product = productRepository.save(product);
        return productMapper.toDto(product);
    }

    @Override
    public ProductDTO updateProduct(UUID id, ProductDTO dto) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));

        if (dto.getTitle() != null) {
            existingProduct.setTitle(dto.getTitle());
        }
        if (dto.getDescription() != null) {
            existingProduct.setDescription(dto.getDescription());
        }
        if (dto.getCategoryId() != null) {
            Category category = categoryService.getCategoryEntityById(dto.getCategoryId());
            existingProduct.setCategory(category);
        }
        existingProduct.setPrice(dto.getPrice());
        existingProduct.setStockQuantity(dto.getStockQuantity());
        existingProduct.setAvailable(dto.isAvailable());

        Product updated = productRepository.save(existingProduct);
        return productMapper.toDto(updated);
    }

    @Override
    public void deleteProduct(UUID id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found with id " + id);
        }
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return productMapper.toDtoList(products);
    }

    @Override
    public List<ProductDTO> getProductsByCategory(UUID categoryId) {
        List<Product> products = productRepository.findByCategory_Id(categoryId);
        return productMapper.toDtoList(products);
    }

    @Override
    public List<ProductDTO> getAvailableProductsWithStock() {
        List<Product> products = productRepository.findByAvailableTrueAndStockQuantityGreaterThan(0);
        return productMapper.toDtoList(products);
    }
}
