package se.lnu.agile.mymanuals.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import se.lnu.agile.mymanuals.converter.CategoryListToCategoryDtoList;
import se.lnu.agile.mymanuals.converter.ProductToProductListDto;
import se.lnu.agile.mymanuals.dao.CategoryDao;
import se.lnu.agile.mymanuals.dao.ConsumerDao;
import se.lnu.agile.mymanuals.dao.ProductDao;
import se.lnu.agile.mymanuals.dao.RepresentativeDao;
import se.lnu.agile.mymanuals.dto.category.CategoryCreateDto;
import se.lnu.agile.mymanuals.dto.category.CategoryDto;
import se.lnu.agile.mymanuals.dto.product.ProductCreateDto;
import se.lnu.agile.mymanuals.dto.product.ProductListDto;
import se.lnu.agile.mymanuals.exception.ProductException;
import se.lnu.agile.mymanuals.model.*;
import se.lnu.agile.mymanuals.service.ProductService;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by ilyakruikov on 11/10/16.
 */
@Service
public class ProductServiceImpl implements ProductService {

    private static final Integer DEFAULT_PAGE = 0;

    private static final Integer DEFAULT_COUNT = 10;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private ConsumerDao consumerDao;

    @Autowired
    private RepresentativeDao representativeDao;

    @Autowired
    private CategoryListToCategoryDtoList categoryListConverter;

    @Autowired
    private ProductToProductListDto productListConverter;

    @Override
    public void createCategory(CategoryCreateDto dto) {
        if (validateCategory(dto.getName())){
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
    public void createProduct(ProductCreateDto dto, String representativeEmail) {
        Company company = representativeDao.findByEmail(representativeEmail).getCompany();
        if (validateProduct(dto, company.getId())) {
            List<Manual> manuals = new ArrayList<>();
            List<Video> videos = new ArrayList<>();
            Product product = new Product(dto.getName(), dto.getModel(), categoryDao.findAll(dto.getCategory()), company, manuals, videos);
            addManuals(dto.getFile(), product, manuals);
            addVideos(dto.getVideo(), product, videos);
            productDao.save(product);
        }
    }

    private void addManuals(List<MultipartFile> files, Product product, List<Manual> manuals) {
        try {
            for (MultipartFile file : files) {
                Manual manual = new Manual(file.getOriginalFilename(), file.getBytes());
                manual.setProduct(product);
                manuals.add(manual);
            }
        } catch(IOException e) {
            String msg = "Failed to process manual file.";
            throw new ProductException(msg);
        }
    }

    private void addVideos(List<String> links, Product product, List<Video> videos) {
        if (links != null) {
            for (String link : links) {
                Video video = new Video(link);
                video.setProduct(product);
                videos.add(video);
            }
        }
    }

    @Override
    public List<ProductListDto> listProducts(List<Long> categoryIds, Integer page, Integer count) {
        List<Product> productList;

        if (validateCategoryByIds(categoryIds)) {
            if (page == null || count == null) {
                page = DEFAULT_PAGE;
                count = DEFAULT_COUNT;
            }
            productList = productDao.findAllByCategoryIds(categoryIds, new PageRequest(page, count)).getContent();
        } else {
            if (page == null || count == null) {
                page = DEFAULT_PAGE;
                count = DEFAULT_COUNT;
            }
            productList = productDao.findAll(new PageRequest(page, count)).getContent();
        }

        return productList == null ? null :
                productList.stream().map(p -> productListConverter.apply(p)).collect(Collectors.toList());
    }

    @Override
    public List<ProductListDto> searchProducts(String query, Integer page, Integer count) {
        List<Product> productList;

        if (query != null) {
            if (page == null || count == null) {
                page = DEFAULT_PAGE;
                count = DEFAULT_COUNT;
            }
            productList = productDao.findAllBySearchQuery(query, new PageRequest(page, count)).getContent();
        } else {
            if (page == null || count == null) {
                page = DEFAULT_PAGE;
                count = DEFAULT_COUNT;
            }
            productList = productDao.findAll(new PageRequest(page, count)).getContent();
        }

        return productList == null ? null :
                productList.stream().map(p -> productListConverter.apply(p)).collect(Collectors.toList());
    }

    @Override
    public void addToFavourites(Long productId, String consumerEmail) {
        Consumer consumer = consumerDao.findByEmail(consumerEmail);
        Product product = productDao.findOne(productId);

        if (validateFavouriteProduct(consumer, product)) {
            if (consumer.getProduct() != null) {
                consumer.getProduct().add(product);
            } else {
                List<Product> products = new ArrayList<>();
                products.add(product);
                consumer.setProduct(products);
            }
            consumerDao.save(consumer);
        }
    }

    @Override
    public List<ProductListDto> listConsumerProducts(String userEmail) {
        List<Product> productList = consumerDao.findByEmail(userEmail).getProduct();
        return productList == null ? null :
                productList.stream().map(p -> productListConverter.apply(p)).collect(Collectors.toList());
    }

    /**
     * Perform validation of the category's data at creation.
     *
     * Checks:
     * -> Category name doesn't exist in the category table
     */
    private boolean validateCategory(String name) {
        if (categoryDao.findByName(name) != null) {
            String msg = "Failed to create category '%s'. A category with such name already exists.";
            throw new ProductException(String.format(msg, name));
        }
        return true;
    }

    /**
     * Perform validation of the parameters list in the listProducts method
     *
     * Check:
     * -> Categories exist in the category table
     */
    public boolean validateCategoryByIds(List<Long> categoryIds) {
        return (categoryIds != null && categoryIds.size() == categoryDao.findAll(categoryIds).size());
    }

    /**
     * Perform validation of product's data at creation.
     *
     * Checks:
     * -> A company cannot have two products with the same model
     * -> Number of categories must not be over 3
     * -> Video links are not over 300 characters long
     */
    private boolean validateProduct(ProductCreateDto dto, Long companyId) {
        if (productDao.getModelByCompanyId(companyId, dto.getModel()) != null) {
            throw new ProductException("Company already has product with such model");
        }

        List<Long> categoryList = dto.getCategory();

        if (categoryList.size() > 3) {
            throw new ProductException("Product can not be more than in 3 categories");
        }

        if (categoryDao.findAll(categoryList).size() != categoryList.size()) {
            throw new ProductException("One of the selected categories does not exist");
        }

        List<String> videoList = dto.getVideo();

        if (videoList != null) {
            for (String link : videoList) {
                if (link.length() > 300) {
                    String msg = "Failed to add product. Video link no" + videoList.indexOf(link) + " should be under 300 characters long";
                    throw new ProductException(msg);
                }
            }
        }

        return true;
    }

    /**
     * Perform validation of the favourite product's data at adding.
     *
     * Checks:
     * -> Consumer and product exist in DB
     * -> Consumer doesn't has current product in favourites
     */
    private boolean validateFavouriteProduct(Consumer consumer, Product product) {
        if (consumer == null || product == null) {
            String msg = "Something went wrong during adding product to favourites (incorrect customer email or product id). Please, try again.";
            throw new ProductException(msg);
        } else if (consumer.getProduct() != null && consumer.getProduct().contains(product)) {
            String msg = "This product is already in your favourites.";
            throw new ProductException(msg);
        }
        return true;
    }

}