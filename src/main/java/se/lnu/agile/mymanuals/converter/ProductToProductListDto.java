package se.lnu.agile.mymanuals.converter;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import se.lnu.agile.mymanuals.dto.category.CategoryDto;
import se.lnu.agile.mymanuals.dto.company.CompanyInfoDto;
import se.lnu.agile.mymanuals.dto.product.ProductListDto;
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

        CompanyInfoDto companyInfoDto = new CompanyInfoDto();
        BeanUtils.copyProperties(product.getCompany(), companyInfoDto);

        productListDto.setId(product.getId());
        productListDto.setName(product.getName());
        productListDto.setModel(product.getModel());
        productListDto.setCategories(categoryDtoList);
        productListDto.setCompany(companyInfoDto);

        return productListDto;
    }

}