package se.lnu.agile.mymanuals.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import se.lnu.agile.mymanuals.converter.*;
import se.lnu.agile.mymanuals.dao.*;
import se.lnu.agile.mymanuals.dto.annotation.ManualAnnotationDto;
import se.lnu.agile.mymanuals.dto.annotation.VideoAnnotationDto;
import se.lnu.agile.mymanuals.dto.category.CategoryCreateDto;
import se.lnu.agile.mymanuals.dto.category.CategoryDto;
import se.lnu.agile.mymanuals.dto.manual.ManualDto;
import se.lnu.agile.mymanuals.dto.product.ProductCreateDto;
import se.lnu.agile.mymanuals.dto.product.ProductDto;
import se.lnu.agile.mymanuals.dto.product.ProductListDto;
import se.lnu.agile.mymanuals.dto.subscription.SubscriptionDto;
import se.lnu.agile.mymanuals.exception.ProductException;
import se.lnu.agile.mymanuals.model.*;
import se.lnu.agile.mymanuals.service.ProductService;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collector;
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
    private ManualDao manualDao;

    @Autowired
    private VideoDao videoDao;

    @Autowired
    private SubscriptionDao subscriptionDao;

    @Autowired
    private ConsumerSubscriptionDao consumerSubscriptionDao;

    @Autowired
    private ManualAnnotationDao manualAnnotationDao;

    @Autowired
    private VideoAnnotationDao videoAnnotationDao;

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private CategoryListToCategoryDtoList categoryListConverter;

    @Autowired
    private ProductToProductListDto productListConverter;

    @Autowired
    private ProductToProductDto productConverter;

    @Autowired
    private ManualToManualDto manualConverter;

    @Autowired
    private SubscriptionToSubscriptionDto subscriptionConverter;

    @Autowired
    private ManualAnnotationListToManualAnnotationDtoList manualAnnotationConverter;

    @Autowired
    private VideoAnnotationListToVideoAnnotationDtoList videoAnnotationConverter;

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
    public List<ProductListDto> listProducts(List<Long> categoryIds, Integer page, Integer count, String consumerEmail) {
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
                checkStoredProductList(productList.stream().map(p -> productListConverter.apply(p)).collect(Collectors.toList()), consumerEmail);
    }

    @Override
    public List<ProductListDto> searchProducts(String query, Integer page, Integer count, String consumerEmail) {
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
                checkStoredProductList(productList.stream().map(p -> productListConverter.apply(p)).collect(Collectors.toList()), consumerEmail);
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
    public ProductDto getProduct(Long productId, String consumerEmail) {
        Product product = productDao.findOne(productId);
        return product == null ? null : checkStoredProduct(productConverter.apply(product), consumerEmail);
    }

    @Override
    public List<ProductListDto> listConsumerProducts(String consumerEmail) {
        List<ProductListDto> result = null;
        List<Product> productList = consumerDao.findByEmail(consumerEmail).getProduct();
        if (productList != null) {
            result = productList.stream().map(p -> productListConverter.apply(p)).collect(Collectors.toList());
            result.stream().forEach(p -> p.setStored(true));
        }
        return result;
    }

    @Override
    public ManualDto getManual(Long manualId) {
        Manual manual = manualDao.findOne(manualId);
        return manual == null ? null : manualConverter.apply(manual);
    }

    @Override
    public void subscribe(Long productId, Long subscriptionId, String consumerEmail) {
        Product product = productDao.findOne(productId);
        Subscription subscription = subscriptionDao.findOne(subscriptionId);
        Consumer consumer = consumerDao.findByEmail(consumerEmail);

        if (validateSubscription(product, subscription, consumer) && validateConsumerSubscription(product, subscription, consumer)) {
            consumerSubscriptionDao.save(new ConsumerSubscription(consumer,product,subscription));
        }
    }

    @Override
    public void unsubscribe(Long productId, Long subscriptionId, String consumerEmail) {
        Product product = productDao.findOne(productId);
        Subscription subscription = subscriptionDao.findOne(subscriptionId);
        Consumer consumer = consumerDao.findByEmail(consumerEmail);
        ConsumerSubscription consumerSubscription = consumerSubscriptionDao.findByConsumerAndProductAndSubscription(consumer, product, subscription);

        if (validateSubscription(product, subscription, consumer) && validateConsumerSubscription(consumerSubscription)) {
            consumerSubscriptionDao.delete(consumerSubscription);
        }
    }

    @Override
    public List<SubscriptionDto> listSubscriptions() {
        List<Subscription> subscriptionList = subscriptionDao.findAll();
        return subscriptionList == null ? null :
                subscriptionList.stream().map(s -> subscriptionConverter.apply(s)).collect(Collectors.toList());
    }

    @Override
    public List<Long> listConsumerSubscriptions(Long productId, String consumerEmail) {
        Consumer consumer = consumerDao.findByEmail(consumerEmail);
        Product product = productDao.findOne(productId);

        if (consumer == null) throw new ProductException("Consumer not existent");
        if (product == null) throw new ProductException("Product not existent");

        return consumerSubscriptionDao.findAllByConsumerAndProduct(consumer,product)
                .stream().map(cs -> cs.getSubscription().getId()).collect(Collectors.toList());
    }

    @Override
    public void addAnnotationToManual(Long manualId, String consumerEmail, String annotation) {
        Manual manual = manualDao.findOne(manualId);
        Consumer consumer = consumerDao.findByEmail(consumerEmail);
        validateManualAnnotation(manual, consumer);
        ManualAnnotation manualAnnotation = new ManualAnnotation(manual, consumer, annotation);
        manualAnnotationDao.save(manualAnnotation);
    }

    @Override
    public void addAnnotationToVideo(Long videoId, String consumerEmail, String annotation) {
        Video video = videoDao.findOne(videoId);
        Consumer consumer = consumerDao.findByEmail(consumerEmail);
        validateVideoAnnotation(video, consumer);
        VideoAnnotation videoAnnotation = new VideoAnnotation(video, consumer, annotation);
        videoAnnotationDao.save(videoAnnotation);

    }

    @Override
    public List<ManualAnnotationDto> listAnnotationsForManual(Long manualId, String consumerEmail) {
        Consumer consumer = consumerDao.findByEmail(consumerEmail);
        List<ManualAnnotation> manualAnnotationList = manualAnnotationDao
                .findByManual_idAndConsumer_id(manualId, consumer.getId());
        return manualAnnotationList == null ? null : manualAnnotationConverter.apply(manualAnnotationList);
    }

    @Override
    public List<VideoAnnotationDto> listAnnotationsForVideo(Long videoId, String consumerEmail) {
        Consumer consumer = consumerDao.findByEmail(consumerEmail);
        List<VideoAnnotation> videoAnnotationList =
                videoAnnotationDao.findByVideo_idAndConsumer_id(videoId, consumer.getId());
        return videoAnnotationList == null ? null : videoAnnotationConverter.apply(videoAnnotationList);
    }

    @Override
    public void addComment(Long productId, String consumerEmail, String comment) {
        Product product = productDao.findOne(productId);
        Consumer consumer = consumerDao.findByEmail(consumerEmail);
        Representative representative = representativeDao.findByEmail(consumerEmail);
        if(consumer == null && representative == null ){
            String msg = "There is no such user";
            throw new ProductException(msg);
        }
        Comment comments = new Comment(consumerEmail, product, comment);
        commentDao.save(comments);
    }

    /**
     * List with all products of the company.
     *
     * @param representativeEmail as identifier for the representative who works for the company
     * @return a list with all products of the company (to which the representative belongs)
     */
    @Override
    public List<ProductListDto> listCompanyProducts(String representativeEmail) {
        Representative representative = representativeDao.findByEmail(representativeEmail);
        List<Product> productList = productDao.findByCompanyId(representative.getCompany().getId());
        return productList == null ? null :
                productList.stream().map(p -> productListConverter.apply(p)).collect(Collectors.toList());
    }

    private ProductDto checkStoredProduct(ProductDto productDto, String consumerEmail){
        if (consumerEmail != null) {
            List<ProductListDto> consumerProductList = listConsumerProducts(consumerEmail);
            consumerProductList.stream().filter(p -> p.getId().equals(productDto.getId())).forEach(p -> productDto.setStored(true));
        }
        return productDto;
    }

    private List<ProductListDto> checkStoredProductList(List<ProductListDto> productList, String consumerEmail) {
        if (consumerEmail != null) {
            List<ProductListDto> consumerProductList = listConsumerProducts(consumerEmail);
            productList.stream().filter(p -> consumerProductList.contains(p)).forEach(p -> p.setStored(true));
        }
        return productList;
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

    /**
     * Perform validation of the subscription's data.
     *
     * Checks:
     * -> Consumer, product and subscription exist in DB
     */
    private boolean validateSubscription(Product product, Subscription subscription, Consumer consumer) {
        if (product == null) {
            throw new ProductException("Unable to find this product in our DB");
        } else if (subscription == null) {
            throw new ProductException("This subscription type does not exist");
        } else if (consumer == null) {
            throw new ProductException("No such user exists");
        }
        return true;
    }

    /**
     * Perform validation of the subscription's data at adding.
     *
     * Checks:
     * -> Consumer are not subscribed for current subscription
     */
    private boolean validateConsumerSubscription(Product product, Subscription subscription, Consumer consumer) {
        if (consumerSubscriptionDao.findByConsumerAndProductAndSubscription(consumer, product, subscription) != null) {
            throw new ProductException("You are already subscribed for this type of subscription and product");
        }
        return true;
    }

    /**
     * Perform validation of the subscription's data at deleting.
     *
     * Checks:
     * -> Consumer are subscribed for current subscription
     */
    private boolean validateConsumerSubscription(ConsumerSubscription consumerSubscription) {
        if (consumerSubscription == null) {
            throw  new ProductException("User is not subscribed for this type of subscription and product");
        }
        return true;
    }

    /**
     * Perform validation of the new manual annotation.
     *
     * Checks:
     * -> Consumer and Manual exist in the database
     */
    private void validateManualAnnotation(Manual manual, Consumer consumer) {
        if (consumer == null) {
            String msg = "Something went wrong during adding your annotation (Unknown user account). Please, try again.";
            throw new ProductException(msg);
        } else if (manual == null) {
            String msg = "Something went wrong during adding your annotation (Unknown manual id). Please, try again.";
            throw new ProductException(msg);
        }
    }

    /**
     * Perform validation of the new video annotation.
     *
     * Checks:
     * -> Consumer and Video exist in the database
     */
    private void validateVideoAnnotation(Video video, Consumer consumer) {
        if (consumer == null) {
            String msg = "Something went wrong during adding your annotation (Unknown user account). Please, try again.";
            throw new ProductException(msg);
        } else if (video == null) {
            String msg = "Something went wrong during adding your annotation (Unknown video id). Please, try again.";
            throw new ProductException(msg);
        }
    }

}