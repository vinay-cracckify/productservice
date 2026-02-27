//package com.cracckify.productservice.services.impl;
//
//import com.cracckify.productservice.Mapper.FakeStoreProductMapper;
//import com.cracckify.productservice.dto.FakeStoreProductDTO;
//import com.cracckify.productservice.models.Product;
//import com.cracckify.productservice.services.ProductService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.ParameterizedTypeReference;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.List;
//import java.util.UUID;
//
//@Service("fakeStoreProductService")
//public class FakeStoreProductServiceImpl implements ProductService {
//
//    private final String BASE_URL = "https://fakestoreapi.com/products";
//
//    @Autowired
//    private RestTemplate restTemplate;
//
//    @Autowired
//    FakeStoreProductMapper fakeStoreProductMapper;
//
//    @Override
//    public Product getProductById(UUID id) {
//        String url = BASE_URL + "/" + id;
//        var fakeProduct = restTemplate.getForObject(url, FakeStoreProductDTO.class);
//        return fakeStoreProductMapper.getProduct(fakeProduct);
//    }
//
//    @Override
//    public Product createProduct(Product product) {
//        var fProduct = fakeStoreProductMapper.getFakeProduct(product);
//        var fakeProduct = restTemplate.postForObject(BASE_URL,fProduct,FakeStoreProductDTO.class);
//        return fakeStoreProductMapper.getProduct(fakeProduct);
//    }
//
//    @Override
//    public Product updateProduct(UUID id, Product product) {
//        String url = BASE_URL + "/" + id;
//        restTemplate.put(url,fakeStoreProductMapper.getFakeProduct(product));
//        return product;
//    }
//
//    @Override
//    public void deleteProduct(UUID id) {
//        String url = BASE_URL + "/" + id;
//        restTemplate.delete(url);
//    }
//
//    @Override
//    public List<Product> getAllProducts() {
//        ResponseEntity<List<FakeStoreProductDTO>> response = restTemplate.exchange(
//                BASE_URL,
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<>() {}
//        );
//        return fakeStoreProductMapper.getProductList(response.getBody());
//    }
//}
