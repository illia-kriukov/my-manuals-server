package se.lnu.agile.mymanuals.service;

import se.lnu.agile.mymanuals.dto.category.CategoryCreateDto;
import se.lnu.agile.mymanuals.dto.category.CategoryDto;
import se.lnu.agile.mymanuals.dto.product.ProductCreateDto;
import se.lnu.agile.mymanuals.dto.product.ProductListDto;

import java.util.List;

/**
 * Created by ilyakruikov on 11/10/16.
 */
public interface ProductService {

    void createCategory(CategoryCreateDto dto);

    List<CategoryDto> listCategories();

    void createProduct(ProductCreateDto dto, String representativeEmail);

    List<ProductListDto> listProducts(Integer page, Integer count, List<Long> categories);

}