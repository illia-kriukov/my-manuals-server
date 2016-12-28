package se.lnu.agile.mymanuals.converter;

import org.springframework.beans.BeanUtils;
import org.springframework.cglib.core.internal.Function;
import org.springframework.stereotype.Component;
import se.lnu.agile.mymanuals.dto.category.CategoryDto;
import se.lnu.agile.mymanuals.model.Category;

/**
 * Created by Daniel on 17.11.2016.
 * A converter from Category to CategoryDto.
 */
@Component
public class CategoryToCategoryDto implements Function<Category, CategoryDto> {

    @Override
    public CategoryDto apply(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        BeanUtils.copyProperties(category, categoryDto);
        return categoryDto;
    }

}