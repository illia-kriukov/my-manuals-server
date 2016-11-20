package se.lnu.agile.mymanuals.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lnu.agile.mymanuals.converter.CategoryListToCategoryDtoList;
import se.lnu.agile.mymanuals.dao.CategoryDao;
import se.lnu.agile.mymanuals.dao.ProductDao;
import se.lnu.agile.mymanuals.dao.RepresentativeDao;
import se.lnu.agile.mymanuals.dto.*;
import se.lnu.agile.mymanuals.exception.RegistrationException;
import se.lnu.agile.mymanuals.model.Category;
import se.lnu.agile.mymanuals.model.Company;
import se.lnu.agile.mymanuals.model.Product;
import se.lnu.agile.mymanuals.model.Video;
import se.lnu.agile.mymanuals.service.ProductService;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by ilyakruikov on 11/10/16.
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private RepresentativeDao representativeDao;

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
    public void createProduct(CreateProductDto dto, String representativeEmail) {
        Company company = representativeDao.findByEmail(representativeEmail).getCompany();
        if (validateProduct(dto, company.getId())) {
            List<Video> videos = new ArrayList<>();
            Product product = new Product(dto.getName(), dto.getModel(), categoryDao.findAll(dto.getCategory()), company, videos);

            for (String link : dto.getVideo()) {
                Video video = new Video(link);
                video.setProduct(product);
                videos.add(video);
            }

            productDao.save(product);
        }
    }

    /**
     * Perform validation of the category's data at Sign-Up.
     *
     * Checks:
     * -> Category name doesn't exists in category table
     */
    private boolean validateCategorySignUp(String name) {
        if (categoryDao.findByName(name) != null) {
            String msg = "Failed to create category '%s'. A category with such name already exists.";
            throw new RegistrationException(String.format(msg, name));
        }
        return true;
    }

    private boolean validateProduct(CreateProductDto dto, Long companyId) {
        return true;
    }

}