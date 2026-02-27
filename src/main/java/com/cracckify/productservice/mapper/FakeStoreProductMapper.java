//package com.cracckify.productservice.Mapper;
//
//import com.cracckify.productservice.dto.FakeStoreProductDTO;
//import com.cracckify.productservice.models.Category;
//import com.cracckify.productservice.models.Product;
//import org.mapstruct.*;
//
//import java.util.List;
//
//@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
//public interface FakeStoreProductMapper {
//
//    @Mapping(source = "category", target = "category", qualifiedByName = "categoryStringToClass")
//    Product getProduct(FakeStoreProductDTO dto);
//
//    List<Product> getProductList(List<FakeStoreProductDTO> dtoList);
//
//    @Named("categoryStringToClass")
//    static Category mapCategory(String categoryTitle) {
//        if (categoryTitle == null) return null;
//        Category category = new Category();
//        category.setTitle(categoryTitle);
//        return category;
//    }
//
//    @Mapping(source = "category", target = "category", qualifiedByName = "classToCategoryString")
//    FakeStoreProductDTO getFakeProduct(Product dto);
//
//    List<FakeStoreProductDTO> getFakeProductList(List<Product> dtoList);
//
//
//    @Named("classToCategoryString")
//    static String mapCategoryToString(Category category) {
//        if (category==null || category.getTitle() == null) return null;
//        return category.getTitle();
//    }
//}