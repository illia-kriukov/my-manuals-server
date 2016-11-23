package se.lnu.agile.mymanuals.converter;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import se.lnu.agile.mymanuals.dto.CategoryDto;
import se.lnu.agile.mymanuals.dto.ProductListDto;
import se.lnu.agile.mymanuals.model.Product;

import java.util.List;
import java.util.function.Function;

/**
 * Created by ToMeg on 2016-11-23.
 */
@Component
public class ProductToProductListDto  implements Function<Product, ProductListDto> {

    @Override
    public ProductListDto apply(Product product) {
        //getting everything we need
        CategoryListToCategoryDtoList converter= new CategoryListToCategoryDtoList();
        ProductListDto productListDto = new ProductListDto();
        List<CategoryDto> categoryDtoList= converter.apply(product.getCategory());
        //setting properties
        productListDto.setName(product.getName());
        productListDto.setModel(product.getModel());
        productListDto.setCategories(categoryDtoList);
        productListDto.setCompanyName(product.getCompany().getName());
        return productListDto;
    }

}
