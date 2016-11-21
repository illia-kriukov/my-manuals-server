package se.lnu.agile.mymanuals.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import se.lnu.agile.mymanuals.converter.CategoryListToCategoryDtoList;
import se.lnu.agile.mymanuals.converter.RepresentativeToRepresentativeDto;
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

import java.security.Principal;
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
    /**Perform validation of products data at Sign-up
     *
     *Checks:
     * -> video links are not over 300 characters long
     * ->Number of categories must not be over 3
     * ->A company cannot have two products with the same model name
     */
    private boolean validateProduct(CreateProductDto dto, Long companyId) {
        List<String> videoList = dto.getVideo();
        List<Long> categoryList = dto.getCategory();
        for (String link : videoList) {
            if (link.length() > 300) {
                String msg = "Failed to add product. Video link no" + videoList.indexOf(link) + " should be under 300 characters long";
                throw new RegistrationException(msg);
            }
        }
        if (categoryList.size() > 3) throw new RegistrationException("Product cannot have more than 3 categories");
        for (Long id : categoryList) {
            if (categoryDao.findOne(id) == null) throw new RegistrationException("Category does not exist");// how to access name
            if (productDao.getModelByCompany(dto.getModel(), companyId) != null) {
                String msg = "This model already exists in the Database";
                throw new RegistrationException(msg);
            }
        }
        return true;
    }
}