package se.lnu.agile.mymanuals.service;

import se.lnu.agile.mymanuals.dto.category.CategoryCreateDto;
import se.lnu.agile.mymanuals.dto.category.CategoryDto;
import se.lnu.agile.mymanuals.dto.manual.ManualDto;
import se.lnu.agile.mymanuals.dto.manual.ManualInfoDto;
import se.lnu.agile.mymanuals.dto.product.ProductCreateDto;
import se.lnu.agile.mymanuals.dto.product.ProductDto;
import se.lnu.agile.mymanuals.dto.product.ProductListDto;

import java.util.List;

/**
 * Created by ilyakruikov on 11/10/16.
 */
public interface ProductService {

    void createCategory(CategoryCreateDto dto);

    List<CategoryDto> listCategories();

    void createProduct(ProductCreateDto dto, String representativeEmail);

    List<ProductListDto> listProducts(List<Long> categoryIds, Integer page, Integer count);

    List<ProductListDto> searchProducts(String query, Integer page, Integer count);

    void addToFavourites(Long productId, String consumerEmail);

    ProductDto getProduct(Long productId);

    List<ProductListDto> listConsumerProducts(String consumerEmail);

    List<ProductListDto> listCompanyProducts(String representativeEmail);

    ManualDto getManual(Long manualId);

}