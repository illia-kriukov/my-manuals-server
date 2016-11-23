package se.lnu.agile.mymanuals.converter;

import org.springframework.stereotype.Component;
import se.lnu.agile.mymanuals.dto.CategoryDto;
import se.lnu.agile.mymanuals.dto.CompanyDto;
import se.lnu.agile.mymanuals.dto.ProductListDto;
import se.lnu.agile.mymanuals.model.Product;

import java.util.List;
import java.util.function.Function;

/**
 * Created by ToMeg on 2016-11-23.
 */
@Component
public class ProductToProductListDto implements Function<Product, ProductListDto> {

    @Override
    public ProductListDto apply(Product product) {
        ProductListDto productListDto = new ProductListDto();

        CategoryListToCategoryDtoList categoryListConverter = new CategoryListToCategoryDtoList();
        List<CategoryDto> categoryDtoList = categoryListConverter.apply(product.getCategory());

        CompanyDto companyDto = new CompanyDto();
        companyDto.setId(product.getCompany().getId());
        companyDto.setName(product.getCompany().getName());

        productListDto.setId(product.getId());
        productListDto.setName(product.getName());
        productListDto.setModel(product.getModel());
        productListDto.setCategories(categoryDtoList);
        productListDto.setCompany(companyDto);

        return productListDto;
    }

}