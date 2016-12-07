package se.lnu.agile.mymanuals.converter;

import javafx.scene.input.Mnemonic;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import se.lnu.agile.mymanuals.dto.category.CategoryDto;
import se.lnu.agile.mymanuals.dto.company.CompanyInfoDto;
import se.lnu.agile.mymanuals.dto.manual.ManualDto;
import se.lnu.agile.mymanuals.dto.product.ProductDto;
import se.lnu.agile.mymanuals.dto.product.ProductListDto;
import se.lnu.agile.mymanuals.model.Product;
import se.lnu.agile.mymanuals.model.Video;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Created by Lo.Gas_2 on 7/12/2016.
 */
@Component
public class ProductToProductDto implements Function<Product, ProductDto> {

    @Override
    public ProductDto apply(Product product) {
        ProductDto productDto = new ProductDto();

        CategoryListToCategoryDtoList categoryListConverter = new CategoryListToCategoryDtoList();
        List<CategoryDto> categoryDtoList = categoryListConverter.apply(product.getCategory());

        CompanyInfoDto companyInfoDto = new CompanyInfoDto();
        BeanUtils.copyProperties(product.getCompany(), companyInfoDto);

        List<String> videos = new ArrayList<String>();
        for(Video video: product.getVideo()){
            videos.add(video.getLink());
        }

        ManualToManualListDto manualToManualListDto = new ManualToManualListDto();
        List<ManualDto> manualsDto = manualToManualListDto.apply(product.getManual());

        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setModel(product.getModel());
        productDto.setCategories(categoryDtoList);
        productDto.setCompany(companyInfoDto);
        productDto.setVideos(videos);
        productDto.setManuals(manualsDto);
        return productDto;
    }
}
