package se.lnu.agile.mymanuals.converter;

import org.springframework.beans.BeanUtils;
import org.springframework.cglib.core.internal.Function;
import org.springframework.stereotype.Component;
import se.lnu.agile.mymanuals.dto.category.CategoryDto;
import se.lnu.agile.mymanuals.model.Category;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Daniel on 18.11.2016.
 * Necessary for listCategories() method.
 */
@Component
public class CategoryListToCategoryDtoList implements Function<List<Category>, List<CategoryDto>> {

    @Override
    public List<CategoryDto> apply(List<Category> categories) {
        List<CategoryDto> categoryDtoList = new LinkedList<>();

        for (Category category : categories) {
            CategoryDto categoryDto = new CategoryDto();
            BeanUtils.copyProperties(category, categoryDto);
            categoryDtoList.add(categoryDto);
        }

        return categoryDtoList;
    }

}