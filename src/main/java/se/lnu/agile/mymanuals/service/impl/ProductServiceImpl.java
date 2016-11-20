package se.lnu.agile.mymanuals.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lnu.agile.mymanuals.converter.CategoryListToCategoryDtoList;
import se.lnu.agile.mymanuals.converter.CompanyToCompanyDto;
import se.lnu.agile.mymanuals.converter.RepresentativeToRepresentativeDto;
import se.lnu.agile.mymanuals.dao.CategoryDao;
import se.lnu.agile.mymanuals.dao.CompanyDao;
import se.lnu.agile.mymanuals.dao.RepresentativeDao;
import se.lnu.agile.mymanuals.dto.*;
import se.lnu.agile.mymanuals.exception.RegistrationException;
import se.lnu.agile.mymanuals.model.Category;
import se.lnu.agile.mymanuals.model.Company;
import se.lnu.agile.mymanuals.model.Representative;
import se.lnu.agile.mymanuals.service.AccountService;
import se.lnu.agile.mymanuals.service.ProductService;

import java.util.List;

/**
 * Created by ilyakruikov on 11/10/16.
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private CategoryListToCategoryDtoList categoryListConverter;

    @Override
    public void createCategory(CategorySignUpDto dto) {
        if (validateCategorySignUp(dto.getName())){
            Category category = new Category(dto.getName());
            categoryDao.save(category);
        }
    }

    @Override
    public List<CategoryDto> listCategories() {
        List<Category> categoryList = categoryDao.findAll();
        return categoryList != null ? categoryListConverter.apply(categoryList) : null;
    }

    @Override
    public void createProduct(ProductDto productDto) {
    }

    /**
     * Perform validation of the category's data at Sign-Up.
     *
     * Checks:
     * -> Category name doesn't exists in category table
     */
    private boolean validateCategorySignUp(String name){
        if (categoryDao.findByName(name) != null) {
            String msg = "Failed to create category '%s'. A category with such name already exists.";
            throw new RegistrationException(String.format(msg, name));
        }
        return true;
    }

}