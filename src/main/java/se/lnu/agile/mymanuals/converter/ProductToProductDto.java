package se.lnu.agile.mymanuals.converter;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.lnu.agile.mymanuals.dto.company.CompanyInfoDto;
import se.lnu.agile.mymanuals.dto.product.ProductDto;
import se.lnu.agile.mymanuals.model.Product;
import se.lnu.agile.mymanuals.model.Video;

import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by Lo.Gas_2 on 7/12/2016.
 */
@Component
public class ProductToProductDto implements Function<Product, ProductDto> {

    @Autowired
    private CategoryListToCategoryDtoList categoryListConverter;

    @Autowired
    private ManualToManualInfoDto manualInfoConverter;

    @Override
    public ProductDto apply(Product product) {
        ProductDto productDto = new ProductDto();

        CompanyInfoDto companyInfoDto = new CompanyInfoDto();
        BeanUtils.copyProperties(product.getCompany(), companyInfoDto);

        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setModel(product.getModel());
        productDto.setCategories(categoryListConverter.apply(product.getCategory()));
        productDto.setCompany(companyInfoDto);
        productDto.setVideos(product.getVideo().stream().map(Video::getLink).collect(Collectors.toList()));
        productDto.setManuals(product.getManual().stream().map(manual -> manualInfoConverter.apply(manual)).collect(Collectors.toList()));

        return productDto;
    }

}